
package utilities;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.transaction.annotation.Transactional;

import utilities.internal.ConsoleReader;
import utilities.internal.DatabaseUtil;
import utilities.internal.SchemaPrinter;
import domain.Trip;

public class FullTextApplication {

	public static void main(final String[] args) throws Throwable {

		final DatabaseUtil du = new DatabaseUtil();
		du.initialise();
		final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(du.getEntityManager());

		FullTextApplication.indexTrips(du, fullTextEntityManager);
		final ConsoleReader cr = new ConsoleReader();
		String line = null;

		System.out.println("\n***** Full-Text Application *****\n\n");
		System.out.println("Enter keyword to find Trip by ticker, title or description: \n");

		do
			try {
				line = cr.readCommand();
				final List<Trip> trips = FullTextApplication.keywordSearch(line, fullTextEntityManager);
				if (trips.isEmpty())
					System.out.println("There aren't coincidences with the keyword inserted.\n");
				else
					SchemaPrinter.print(trips);
			} catch (final Throwable oops) {

			}
		while (true);
	}

	@Transactional
	public static void indexTrips(final DatabaseUtil du, final FullTextEntityManager fullTextEntityManager) throws Exception {

		try {
			du.getEntityManager().getTransaction().begin();
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (final Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Trip> keywordSearch(final String keywordSearch, final FullTextEntityManager fullTextEntityManager) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		final QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Trip.class).get();
		final org.apache.lucene.search.Query query = qb.keyword().onFields("ticker", "title", "description").matching(keywordSearch).createQuery();
		final Query fullSearchQuery = fullTextEntityManager.createFullTextQuery(query, Trip.class);
		final List<Trip> result = fullSearchQuery.getResultList();
		return result;
	}

}
