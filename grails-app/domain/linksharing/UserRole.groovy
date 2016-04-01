package linksharing

import grails.gorm.DetachedCriteria
import groovy.transform.ToString


@ToString(cache=true, includeNames=true, includePackage=false)
class UserRole implements Serializable {

	private static final long serialVersionUID = 1

	User user
	Role role

	UserRole(User u, Role r) {
		this()
		user = u
		role = r
	}


	static UserRole get(long userId, long roleId) {
		criteriaFor(userId, roleId).get()
	}

	static boolean exists(long userId, long roleId) {
		criteriaFor(userId, roleId).count()
	}

	private static DetachedCriteria criteriaFor(long userId, long roleId) {
		UserRole.where {
			user == User.load(userId) &&
			role == Role.load(roleId)
		}
	}

	static UserRole create(User user, Role role, boolean flush = false) {
		def instance = new UserRole(user: user, role: role)
		instance.save(flush: flush, insert: true)
		instance
	}

	boolean equals(o) {
		if (this.is(o)) return true
		if (!(o instanceof UserRole)) return false

		UserRole userRole = (UserRole) o

		if (id != userRole.id) return false
		if (role != userRole.role) return false
		if (user != userRole.user) return false
		if (version != userRole.version) return false

		return true
	}

	int hashCode() {
		int result
		result = (user != null ? user.hashCode() : 0)
		result = 31 * result + (role != null ? role.hashCode() : 0)
		result = 31 * result + (id != null ? id.hashCode() : 0)
		result = 31 * result + (version != null ? version.hashCode() : 0)
		return result
	}

	static boolean remove(User u, Role r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = UserRole.where { user == u && role == r }.deleteAll()

		if (flush) { UserRole.withSession { it.flush() } }

		rowCount
	}

	static void removeAll(User u, boolean flush = false) {
		if (u == null) return

		UserRole.where { user == u }.deleteAll()

		if (flush) { UserRole.withSession { it.flush() } }
	}

	static void removeAll(Role r, boolean flush = false) {
		if (r == null) return

		UserRole.where { role == r }.deleteAll()

		if (flush) { UserRole.withSession { it.flush() } }
	}

	static constraints = {
		role validator: { Role r, UserRole ur ->
			if (ur.user == null || ur.user.id == null) return
			boolean existing = false
			UserRole.withNewSession {
				existing = UserRole.exists(ur.user.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['user', 'role']
		version false
	}
}
