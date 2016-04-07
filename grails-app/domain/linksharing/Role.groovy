package linksharing

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='authority')
@ToString(includes='authority', includeNames=true, includePackage=false)
class Role implements Serializable {

	private static final long serialVersionUID = 1

	String authority

	Role(String authority) {
		this()
		this.authority = authority
	}

	static constraints = {
		authority blank: false, unique: true
	}

	boolean equals(o) {
		if (this.is(o)) return true
		if (!(o instanceof Role)) return false

		Role role = (Role) o

		if (authority != role.authority) return false
		if (id != role.id) return false
		if (version != role.version) return false

		return true
	}

	int hashCode() {
		int result
		result = (authority != null ? authority.hashCode() : 0)
		result = 31 * result + (id != null ? id.hashCode() : 0)
		result = 31 * result + (version != null ? version.hashCode() : 0)
		return result
	}
	static mapping = {
		cache true
	}
}
