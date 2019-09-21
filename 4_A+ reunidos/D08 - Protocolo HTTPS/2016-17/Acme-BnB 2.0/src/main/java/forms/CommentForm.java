package forms;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;


public class CommentForm {

	//Attributes=====================================================================================
	
	private String	title;
	private String	text;
	private int	stars;

	//Getters & setters================================================================================	
	
	@NotBlank
	@SafeHtml
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	@SafeHtml
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@Range(min = 0, max = 5)
	public int getStars() {
		return stars;
	}
	
	public void setStars(int stars) {
		this.stars = stars;
	}
}
