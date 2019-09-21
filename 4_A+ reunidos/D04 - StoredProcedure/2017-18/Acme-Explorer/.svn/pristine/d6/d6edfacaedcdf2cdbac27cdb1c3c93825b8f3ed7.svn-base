
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.Folder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FolderServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private FolderService	folderService;

	@Autowired
	private ActorService	actorService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {

		Folder folder;

		super.authenticate("ranger1");
		folder = this.folderService.create(this.actorService.findByPrincipal(), false, null);
		Assert.isNull(folder.getName());
		Assert.isTrue(folder.getPredefined() == false);
		Assert.notNull(folder.getActor());
		Assert.isNull(folder.getParent());
		Assert.notNull(folder.getChildren());
		Assert.notNull(folder.getMessages());
		super.authenticate(null);
	}

	@Test
	public void testSave() {

		final Folder folder;
		Folder saved;

		super.authenticate("ranger1");
		folder = this.folderService.create(this.actorService.findByPrincipal(), false, null);
		folder.setName("Folder test");
		saved = this.folderService.save(folder);
		final Collection<Folder> folders = this.folderService.findByPrincipal();
		Assert.isTrue(folders.contains(saved));
		super.authenticate(null);
	}

	@Test
	public void testDelete() {

		final Folder folder;
		Folder saved;

		super.authenticate("ranger1");
		folder = this.folderService.create(this.actorService.findByPrincipal(), false, null);
		folder.setName("Folder test");
		saved = this.folderService.save(folder);
		this.folderService.delete(saved);
		final Collection<Folder> folders = this.folderService.findByPrincipal();
		Assert.isTrue(!folders.contains(saved));
		super.authenticate(null);
	}

	@Test
	public void testFindOneToEditPositive() {

		Folder folder, saved, folderRetrieved;

		super.authenticate("admin1");
		folder = this.folderService.create(this.actorService.findByPrincipal(), false, null);
		folder.setName("Folder test");
		saved = this.folderService.save(folder);
		folderRetrieved = this.folderService.findOneToEdit(saved.getId());
		Assert.isTrue(saved.equals(folderRetrieved));
		super.authenticate(null);
	}

	@Test
	public void testFindOneToEditNegative() {

		Folder folder, saved, folder1;

		super.authenticate("admin1");
		folder = this.folderService.create(this.actorService.findByPrincipal(), false, null);
		folder.setName("Folder test");
		saved = this.folderService.save(folder);
		folder1 = this.folderService.findOneToEdit(saved.getId());
		super.authenticate(null);
		super.authenticate("auditor1");
		try {
			final Folder folder2 = this.folderService.findOneToEdit(folder1.getId());
		} catch (final Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void testFindByPrincipal() {

		Collection<Folder> folders;

		super.authenticate("admin1");
		final Actor actor = this.actorService.findByPrincipal();
		folders = this.folderService.findByPrincipal();
		for (final Folder f : folders)
			Assert.isTrue(f.getActor() == actor);
		super.authenticate(null);
	}
}
