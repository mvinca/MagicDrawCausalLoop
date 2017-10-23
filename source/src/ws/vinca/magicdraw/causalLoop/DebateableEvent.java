package ws.vinca.magicdraw.causalLoop;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;

public class DebateableEvent implements ICausalEvent {

	private Association link;
	private Property property;
	private Boolean debateable;
	
	@Override
	public Association getLink() {
		return link;
	}
	
	public void setLink(Association link) {
		this.link = link;
	}

	@Override
	public Property getProperty() {
		return property;
	}
	
	public void setProperty(Property property) {
		this.property = property;
	}

	@Override
	public Object getValue() {
		return debateable;
	}
	
	public Boolean isDebateable() {
		return debateable;
	}

	public void setDebateable(Boolean debateable) {
		this.debateable = debateable;
	}
}
