package fetch.pf;

import com.google.maps.errors.ApiException;

import java.io.IOException;

public interface Filter<T, O> {

	O execute(Object input) throws IOException, InterruptedException, ApiException;

}
