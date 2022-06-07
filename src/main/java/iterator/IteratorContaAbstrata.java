package iterator;

import com.jbank.banco.ContaAbstrata;

public interface IteratorContaAbstrata {
	boolean hasNext();
	ContaAbstrata next();
}
