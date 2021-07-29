import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CheckList implements Runnable {

	private String name;
	private List<Integer> list;

	public CheckList(String name, List<Integer> list) {
		this.name = name;
		this.list = list;
	}

	@Override
	public void run() {
		List<Integer> check = new ArrayList<>();
		check = list.stream().filter(value -> value % 5 == 0).collect(Collectors.toList());
		//check.forEach(n -> System.out.println(name + ": " + n));
	}

}
