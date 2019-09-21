
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StoryRepository;
import domain.Explorer;
import domain.Story;
import domain.Trip;

@Service
@Transactional
public class StoryService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private StoryRepository	storyRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ExplorerService	explorerService;

	@Autowired
	private TripService		tripService;


	// Constructors -----------------------------------------------------------

	public StoryService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Story create(final Trip trip) {

		Explorer writer = null;
		Story result = null;

		writer = this.explorerService.findByPrincipal();
		Assert.isInstanceOf(Explorer.class, writer);

		Assert.notNull(writer);

		result = new Story();

		result.setAttachments(new ArrayList<String>());

		result.setTrip(trip);
		result.setWriter(writer);

		return result;
	}

	public Collection<Story> findAll() {

		Collection<Story> result = null;

		result = this.storyRepository.findAll();

		return result;

	}

	public Story findOne(final int storyId) {

		Story result = null;

		result = this.storyRepository.findOne(storyId);

		return result;

	}

	public Story save(final Story story) {

		Assert.notNull(story);
		Story result = null;

		result = this.storyRepository.save(story);

		return result;
	}

	public void delete(final Story story) {

		Assert.notNull(story);
		this.storyRepository.delete(story);

	}

	// Other business methods -------------------------------------------------

	public void deleteStories(final Trip trip) {
		final Collection<Story> stories = trip.getStories();
		this.storyRepository.delete(stories);

	}

	public void deleteByExplorer(final Explorer explorer) {

		Assert.notNull(explorer);

		final Collection<Story> stories = explorer.getStories();
		this.storyRepository.delete(stories);
		explorer.getStories().removeAll(stories);
	}

	public Collection<Story> findByExplorer(final Explorer explorer) {

		Assert.notNull(explorer);

		Collection<Story> result = null;
		result = this.storyRepository.findByExplorer(explorer);
		return result;
	}

	public Story assignStoryToTrip(final Story story, final Trip trip) {

		Assert.notNull(trip);
		Assert.notNull(story);

		this.checkByPrincipalExplorer(story);
		story.setTrip(trip);
		trip.getStories().add(story);
		this.tripService.save(trip);
		this.save(story);

		return story;
	}

	private void checkByPrincipalExplorer(final Story story) {
		Explorer explorer = null;

		explorer = this.explorerService.findByPrincipal();
		Assert.isTrue(story.getWriter().equals(explorer));
	}
}
