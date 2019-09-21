package controllers.actor;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.FolderService;
import controllers.AbstractController;
import domain.Actor;
import domain.Folder;

@Controller
@RequestMapping("/folder/actor")
public class FolderActorController extends AbstractController{

	// Services =======================================================================================

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private FolderService folderService;

	// Constructors =======================================================================================
	
	public FolderActorController() {
		super();
	}
	
	// List =======================================================================================
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Folder> folders;
		Actor principal;
		Boolean children;

		principal = actorService.findByPrincipal();
		folders = folderService.findAllByUserAccount(principal.getUserAccount());
		children = false;

		result = new ModelAndView("folder/list");

		result.addObject("folders", folders);
		result.addObject("requestURI", "folder/actor/list.do");
		result.addObject("children", children);

		return result;
	}
	
	// Folder children list =======================================================================================
	
	@RequestMapping(value = "/childrenList", method = RequestMethod.GET)
	public ModelAndView childrenList(@RequestParam int folderId) {
		ModelAndView result;
		Collection<Folder> folders;
		Folder parent;
		String parentName;
		Boolean children;

		parent = folderService.findOneByPrincipal(folderId);

		folders = parent.getChildren();
		parentName = parent.getName();
		children = true;

		result = new ModelAndView("folder/list");

		result.addObject("folders", folders);
		result.addObject("requestURI", "folder/actor/childrenList.do");
		result.addObject("parentName", parentName);
		result.addObject("children", children);
		result.addObject("parentId", folderId);

		return result;
	}
	
	// Create folder =======================================================================================
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Folder folder;
		Actor principal;
		Boolean isNew;

		principal = actorService.findByPrincipal();
		folder = folderService.createByActor(principal);
		isNew = true;

		result = new ModelAndView("folder/create");

		result.addObject("folder", folder);
		result.addObject("requestURI", "folder/actor/create.do");
		result.addObject("isNew", isNew);

		return result;
	}
	
	// Save create folder =======================================================================================
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Folder folder, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			result = createModelAndView(folder);
		} else {
			try {
				folderService.saveFolderByActor(folder);

				result = new ModelAndView("redirect:../../folder/actor/list.do");

			} catch (Throwable oops) {
				result = createModelAndView(folder, "folder.commit.error");
			}
		}
		return result;

	}
	
	// Edit folder =======================================================================================
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int folderId) {
		ModelAndView result;
		Folder folder;
		Actor principal;
		Boolean isNew;
		Boolean isOwner;
		Boolean eraseable;
		Boolean hasChildren;
		Boolean systemFolder;

		principal = actorService.findByPrincipal();
		folder = folderService.findOneByPrincipal(folderId);
		isNew = false;
		isOwner = folder.getUserAccount().equals(principal.getUserAccount());
		eraseable = folder.getChildren().isEmpty()
				&& folder.getMessages().isEmpty()
				&& !folder.getSystemFolder();
		hasChildren = !folder.getChildren().isEmpty();
		systemFolder = folder.getSystemFolder();

		result = new ModelAndView("folder/edit");

		result.addObject("folder", folder);
		result.addObject("requestURI", "folder/actor/edit.do");
		result.addObject("isNew", isNew);
		result.addObject("isOwner", isOwner);
		result.addObject("eraseable", eraseable);
		result.addObject("folderId", folderId);
		result.addObject("hasChildren", hasChildren);
		result.addObject("systemFolder", systemFolder);

		return result;
	}
	
	// Save edit folder =======================================================================================
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Folder folder, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			result = editModelAndView(folder);
		} else {
			try {
				folderService.saveFolderByActor(folder);

				result = new ModelAndView("redirect:../../folder/actor/list.do");

			} catch (Throwable oops) {
				result = editModelAndView(folder, "folder.commit.error");
			}
		}
		return result;

	}
	
	// Delete folder ======================================================================================
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Folder folder, BindingResult binding) {
		ModelAndView result;

		try {
			folderService.delete(folder);
			result = new ModelAndView("redirect:../../folder/actor/list.do");
		} catch (Throwable oops) {
			result = editModelAndView(folder, "folder.commit.error");
		}

		return result;
	}
	
	// Create child folder ======================================================================================
	
	@RequestMapping(value = "/createChild", method = RequestMethod.GET)
	public ModelAndView createChild(@RequestParam int folderId) {
		ModelAndView result;
		Folder folder;
		Folder parent;
		Actor principal;
		Boolean isNew;
		Boolean createChild;
		String parentName;

		principal = actorService.findByPrincipal();
		parent = folderService.findOneByPrincipal(folderId);
		folder = folderService.createChildFolder(principal, parent);
		parentName = parent.getName();
		createChild = true;

		isNew = true;

		result = new ModelAndView("folder/createChild");

		result.addObject("folder", folder);
		result.addObject("requestURI", "folder/actor/createChild.do?folderId=" + folderId);
		result.addObject("isNew", isNew);
		result.addObject("createChild", createChild);
		result.addObject("parentName", parentName);

		return result;

	}

	// Save create child folder ======================================================================================
	
	@RequestMapping(value = "/createChild", method = RequestMethod.POST, params = "save")
	public ModelAndView saveChild(@Valid Folder folder, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			result = createChildModelAndView(folder);
		} else {
			try {
				folderService.saveFolderByActor(folder);

				result = new ModelAndView("redirect:../../folder/actor/list.do");

			} catch (Throwable oops) {
				result = createChildModelAndView(folder, "folder.commit.error");
			}
		}
		return result;
	}
	
	// Move message to a folder =======================================================================================
	
	@RequestMapping(value = "/moveTo", method = RequestMethod.GET)
	public ModelAndView moveMessageToFolder(@RequestParam int sourceFolderId,
			@RequestParam int messageId) {
		ModelAndView result;
		Collection<Folder> folders;
		Actor principal;
		Folder actualFolder;

		principal = actorService.findByPrincipal();
		folders = folderService.findAllByUserAccount(principal.getUserAccount());
		actualFolder = folderService.findOneByPrincipal(sourceFolderId);

		folders.remove(actualFolder);

		result = new ModelAndView("folder/moveTo");
		result.addObject("folders", folders);
		result.addObject("requestURI", "folder/actor/moveTo.do?messageId="
				+ messageId);
		result.addObject("messageId", messageId);
		result.addObject("sourceFolderId", sourceFolderId);

		return result;

	}
	
	// Ancillary methods: Create folder =======================================================================================
	
	protected ModelAndView createModelAndView(Folder folder) {
		ModelAndView result;
		result = createModelAndView(folder, null);
		return result;

	}

	protected ModelAndView createModelAndView(Folder folder, String message) {
		ModelAndView result;
		Boolean isNew;

		isNew = true;
		result = new ModelAndView("folder/create");

		result.addObject("folder", folder);
		result.addObject("requestURI", "folder/actor/create.do");
		result.addObject("message", message);
		result.addObject("isNew", isNew);

		return result;

	}
	
	// Ancillary methods: Edition =======================================================================================
	
	protected ModelAndView editModelAndView(Folder folder) {
		ModelAndView result;
		result = editModelAndView(folder, null);
		return result;

	}

	protected ModelAndView editModelAndView(Folder folder, String errorMessage) {
		ModelAndView result;
		Actor principal;
		Boolean isNew;
		Boolean isOwner;
		Boolean eraseable;
		Boolean hasChildren;
		Boolean systemFolder;

		principal = actorService.findByPrincipal();
		isNew = false;
		isOwner = folder.getUserAccount().equals(principal.getUserAccount());
		eraseable = folder.getChildren().isEmpty()
				&& folder.getMessages().isEmpty()
				&& !folder.getSystemFolder();
		hasChildren = !folder.getChildren().isEmpty();
		systemFolder = folder.getSystemFolder();

		result = new ModelAndView("folder/create");

		result.addObject("errorMessage", errorMessage);
		result.addObject("folder", folder);
		result.addObject("requestURI", "folder/actor/edit.do");
		result.addObject("isNew", isNew);
		result.addObject("isOwner", isOwner);
		result.addObject("eraseable", eraseable);
		result.addObject("hasChildren", hasChildren);
		result.addObject("systemFolder", systemFolder);

		return result;
	}
	
	// Ancillary methods: Create child folder =======================================================================================
	
	protected ModelAndView createChildModelAndView(Folder folder) {
		ModelAndView result;
		result = createChildModelAndView(folder, null);
		return result;

	}

	protected ModelAndView createChildModelAndView(Folder folder,
		String message) {
		ModelAndView result;
		Boolean isNew;
		Boolean createChild;
		String parentName;

		isNew = true;
		createChild = true;
		parentName = folder.getParent().getName();
		result = new ModelAndView("folder/createChild");

		result.addObject("folder", folder);
		result.addObject("requestURI", "folder/actor/createChild.do");
		result.addObject("message", message);
		result.addObject("isNew", isNew);
		result.addObject("createChild", createChild);
		result.addObject("parentName", parentName);

		return result;
	}
	
}
