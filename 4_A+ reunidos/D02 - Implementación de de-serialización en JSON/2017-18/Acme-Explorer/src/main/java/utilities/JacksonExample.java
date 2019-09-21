
package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;

import security.Authority;
import security.UserAccount;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonExample {

	public static void main(final String[] args) {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

		final SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
		mapper.setDateFormat(sdf);

		final UserAccount userAccount = new UserAccount();
		userAccount.setId(7);
		userAccount.setUsername("json");
		userAccount.setPassword("466deec76ecdf5fca6d38571f6324d54");
		userAccount.setDesactivated(false);

		final Authority authority = new Authority();
		authority.setAuthority("SPONSOR");
		userAccount.addAuthority(authority);

		System.out.println("INICIALIZATION OF THE OBJECT: \n\n" + userAccount);
		System.out.println("------------");

		String s = null;
		try {
			s = mapper.writeValueAsString(userAccount);
		} catch (final JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("SERIALIZATION OF THE OBJECT: \n\n" + s);
		System.out.println("------------");

		UserAccount userAccount2 = null;
		try {
			userAccount2 = mapper.readValue(s, UserAccount.class);
		} catch (final JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("DESERIALIZATION OF THE JSON: \n\n" + userAccount2);
	}
}
