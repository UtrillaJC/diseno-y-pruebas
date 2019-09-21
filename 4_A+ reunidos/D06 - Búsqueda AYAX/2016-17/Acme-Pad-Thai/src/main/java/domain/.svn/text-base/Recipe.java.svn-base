/* Recipe.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package domain;

import java.net.URL;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Recipe extends DomainEntity {

	// Attributes ====================================================================================
	
	private String ticker;
	private String title;
	private String summary;
	private Date momentAuthored;
	private Date momentUpdated;
	private Collection<URL> pictures;
	private Collection<String> hints;
	private int likes;
	private int dislikes;
	
	// Constructors ====================================================================================

	public Recipe() {
		super();
	}
	
	//Getters & setters ====================================================================================
	
	@Column(unique=true)
	@NotBlank
	@Pattern(regexp = "\\d{6}(-\\D{4})")
	public String getTicker() {
		return ticker;
	}
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMomentAuthored() {
		return momentAuthored;
	}
	
	public void setMomentAuthored(Date momentAuthored) {
		this.momentAuthored = momentAuthored;
	}
	
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMomentUpdated() {
		return momentUpdated;
	}

	public void setMomentUpdated(Date momentUpdated) {
		this.momentUpdated = momentUpdated;
	}

	@ElementCollection
	public Collection<URL> getPictures() {
		return pictures;
	}

	public void setPictures(Collection<URL> pictures) {
		this.pictures = pictures;
	}

	@ElementCollection
	public Collection<String> getHints() {
		return hints;
	}

	public void setHints(Collection<String> hints) {
		this.hints = hints;
	}
	
	@Min(0)
	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	@Min(0)
	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	//Relationships ====================================================================================	

	private User user;
	private Collection<Contest> contestsWon;
	private Collection<IngredientLine> ingredientLines;
	private Collection<StepToCook> stepsToCook;
	private Collection<Category> categories;
	private Collection<Registration> registrations;
	private Collection<Taste> tastes;
	
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Valid
	@ManyToMany(mappedBy = "winners")
	public Collection<Contest> getContestsWon() {
		return contestsWon;
	}

	public void setContestsWon(Collection<Contest> contestsWon) {
		this.contestsWon = contestsWon;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy = "recipe")
	public Collection<IngredientLine> getIngredientLines() {
		return ingredientLines;
	}

	public void setIngredientLines(Collection<IngredientLine> ingredientLines) {
		this.ingredientLines = ingredientLines;
	}

	@Valid
	@NotNull
	@OneToMany(cascade=CascadeType.ALL, mappedBy="recipe")
	public Collection<StepToCook> getStepsToCook() {
		return stepsToCook;
	}

	public void setStepsToCook(Collection<StepToCook> stepsToCook) {
		this.stepsToCook = stepsToCook;
	}

	@Valid
	@NotNull
	@ManyToMany
	public Collection<Category> getCategories() {
		return categories;
	}

	public void setCategories(Collection<Category> categories) {
		this.categories = categories;
	}
	
	@Valid
	@OneToMany(mappedBy="recipe")
	public Collection<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(Collection<Registration> registrations) {
		this.registrations = registrations;
	}

	@Valid
	@OneToMany(mappedBy="recipe", cascade = CascadeType.ALL)
	public Collection<Taste> getTastes() {
		return tastes;
	}

	public void setTastes(Collection<Taste> tastes) {
		this.tastes = tastes;
	}
}
