package nl.han.ica.icss.checker;

public class SemanticError {
	public String description;

	public SemanticError(String description) {
		this.description = description;
	}
	public String toString() {
		return "ERROR: " + description;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		SemanticError other = (SemanticError) obj;
		return this.description.equals(other.description);
	}
}
