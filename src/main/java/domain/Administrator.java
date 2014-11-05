
package domain;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends Actor{
}