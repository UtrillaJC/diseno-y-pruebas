package services;

import java.net.URL;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.VideoRepository;
import domain.Cook;
import domain.MasterClass;
import domain.Video;

@Service
@Transactional
public class VideoService {
	
	//Managed Repository =============================================================================
	
	@Autowired
	private VideoRepository videoRepository;
	
	//Supported Services =============================================================================

	@Autowired
	private CookService cookService;
	
	//Constructor methods ============================================================================
	
	public VideoService(){
		super();
	}
	
	//Simple CRUD methods ============================================================================
	
	public Video findOne(int videoId){
		Video result;
		
		result = videoRepository.findOne(videoId);
		
		return result;
	}
	
	public Video create(MasterClass masterClass){
		Assert.notNull(masterClass);
		Assert.notNull(masterClass.getCook());
		Video result;
		Cook principal;
		Collection<URL> attachments;
		
		principal = cookService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Cook.class, principal);
		Assert.isTrue(principal.equals(masterClass.getCook()));
		attachments = new HashSet<URL>();

		result = new Video();
		
		result.setMasterClass(masterClass);
		result.setAttachments(attachments);
		result.setType("Video");
		
		return result;	
	}
	
	public Video save(Video video){
		Assert.notNull(video);
		Assert.notNull(video.getMasterClass().getCook());
		Video result;
		Cook principal;
		
		principal = cookService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Cook.class, principal);
		Assert.isTrue(principal.equals(video.getMasterClass().getCook()));
				
		result = videoRepository.save(video);
		
		return result;
	}
	
	//Other Business Methods =========================================================================

}
