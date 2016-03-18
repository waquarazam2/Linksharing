package linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

import javax.xml.transform.Result

@TestFor(User)
class UserSpec extends Specification {
    User user = new User(email: "waquar.azam@tothenew.com", userName: "waquar1234", firstName: "Waquar",
            lastName: "Azam", admin: true, active: true, password: "abcdefgh")

    void "get name"() {
        given:
        user.firstName = firstName

        and:
        user.lastName = lastName

        expect:
        user.name == result

        where:
        firstName | lastName | result
        'Waquar'  | 'Azam'   | 'Waquar Azam'
        'Mohit'   | 'Singh'  | 'Mohit Singh'
    }

    void "validate userEmail"() {
        given:
        user.email = email

        when:
        user.validate()
        then:
        user.errors.getFieldError('email')?.code == result

        where:
        email                   | result
        "waquarazam2@gmail.com" | null
        ""                      | 'blank'
        null                    | 'nullable'
        "waquar"                | 'email.invalid'
    }

    void "validate userPassword"() {
        given:
        user.password = password

        when:
        user.validate()
        then:
        user.errors.getFieldError('password')?.code == result

        where:
        password   | result
        "abc"      | 'minSize.notmet'
        "abcdefgh" | null
        "12345"    | null
        ""         | 'blank'
        null       | 'nullable'
    }

    def "validate firstName"() {
        given:
        user.firstName = name

        when:
        user.validate()

        then:
        user.errors.getFieldError("firstName")?.code == result

        where:
        name | result
        ''   | 'blank'
        null | 'nullable'

    }

    def "validate lastName"() {
        given:
        user.lastName = name

        when:
        user.validate()

        then:
        user.errors.getFieldError("lastName")?.code == result

        where:
        name | result
        ''   | 'blank'
        null | 'nullable'

    }

    def "validate isAdmin"() {
        given:
        user.admin = isAdmin

        when:
        user.validate()

        then:
        user.errors.getFieldError("admin")?.code == result

        where:
        isAdmin | result
        true    | null
        null    | null
        false   | null
    }

    def "validate isActive"() {
        given:
        user.admin = isActive

        when:
        user.validate()

        then:
        user.errors.getFieldError("active")?.code == result

        where:
        isActive | result
        true     | null
        null     | null
        false    | null
    }

    void "TestingToString"() {
        setup:
        User user = new User(firstName: 'Waquar',lastName: 'Azam')

        expect:
        user.toString()=='Waquar Azam'
    }
}
