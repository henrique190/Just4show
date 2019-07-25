package Script;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindUrls {
	public static List<String> extractUrls(String input) {
		List<String> result = new ArrayList<String>();

		Pattern pattern = Pattern
				.compile("(ftp:\\/\\/|www\\.|https?:\\/\\/){1}[a-zA-Z0-9u00a1-\\uffff0-]{2,}\\.[a-zA-Z0-9u00a1-\\uffff0-]{2,}(\\S*)");

		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			result.add(matcher.group());
		}

		return result;
	}
}
