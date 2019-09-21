
package utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class storedProcedure {

	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		EntityManagerFactory entityManagerFactory;
		EntityManager entitymanager;

		entityManagerFactory = Persistence.createEntityManagerFactory("Acme-Explorer");
		entitymanager = entityManagerFactory.createEntityManager();

		final Query query = entitymanager.createNativeQuery("call getTripsAcceptedByExplorer()");
		final List<Object> result = query.getResultList();

		final String newLine = System.lineSeparator();

		for (final Object object : result) {
			Object obje[] = new Object[10];
			obje = (Object[]) object;
			System.out.println(newLine);
			System.out.println("###" + obje[0] + " ###");
			System.out.println("Trips accepted: " + obje[1]);
			System.out.println("Quartile: " + obje[2]);
		}
		entitymanager.close();
		entityManagerFactory.close();
	}
}
