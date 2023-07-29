package com.problems.crackcode.kata.threading.executors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

//assuming this is a Service class
public class TravelQuote {

	public List<TravelQuote> getQuoteSingleThread(TravelPlan plan, List<TravelCompany> companies) {
		List<TravelQuote> quotes = new ArrayList<>();
		for (TravelCompany company : companies) {
			TravelQuote quote = _callCompanyToGetQuote(plan);
			quotes.add(quote);
		}
		return quotes;
	}

	//since these calls are homgeneours, we can divide these among multiple workers
	public List<TravelQuote> getQuoteFastMultithreaded(TravelPlan plan, List<TravelCompany> companies, Comparator<TravelQuote> customSortOrder)
			throws InterruptedException {
		ExecutorService exsc = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		List<TravelQuote> quotes = new ArrayList<>();
		List<Callable<TravelQuote>> tasks = new ArrayList<>();
		for (TravelCompany company : companies) {
			tasks.add(() -> _callCompanyToGetQuote(plan));
		}
		List<Future<TravelQuote>> futures = exsc.invokeAll(tasks, 5, TimeUnit.SECONDS);

		for (Future<TravelQuote> future : futures) {
			try {
				quotes.add(future.get());
			} catch (ExecutionException e) {
				quotes.add(_errorOrDefaultQuote());
			} catch (CancellationException e) {
				quotes.add(_errorOrDefaultQuote());
			}
		}

		Collections.sort(quotes, customSortOrder);
		return quotes;
	}


	private TravelQuote _errorOrDefaultQuote() {
		return new TravelQuote();
	}

	private TravelQuote _callCompanyToGetQuote(TravelPlan plan) {
		//this will be either a proto call, HTTP call, EDI call (most probably HTTP)
		return null;
	}


}

class TravelCompany {
}

class Quote {
	int cost;
}

class TravelPlan {
	StartPlace start;
	DestinationPlace destination;
	Budget budget;
	TravelDates dates;
}

class TravelDates {
}

class Budget {

}

interface Place {

}

class StartPlace implements Place {
}

class DestinationPlace implements Place {
}