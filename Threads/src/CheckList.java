import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CheckList implements Runnable {

	private List<Integer> list;

	public CheckList(List<Integer> list) {
		this.list = list;
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		List<Integer> check = new ArrayList<>();
		check = list.stream().filter(value -> value % 5 == 0).collect(Collectors.toList());
		check.forEach(n -> System.out.println(threadName + ": " + n));
	}

}
