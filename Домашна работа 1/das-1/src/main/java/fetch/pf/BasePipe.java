package fetch.pf;

import com.google.maps.errors.ApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasePipe<T, O> {
	private List<Filter<?, ?>> filters = new ArrayList<>();

	public void addFilter(Filter<?, ?> filter) {
		filters.add(filter);
	}

	public O runFilters(T input) throws IOException, InterruptedException, ApiException {
		Object nextParam = input;
		for (Filter<?, ?> filter : filters) {
			nextParam = filter.execute(nextParam);
		}
		return (O) nextParam;
	}
}
