package ws.vinca.magicdraw.causalLoop;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;

public class DelayedEvent implements ICausalEvent {

	private Association link;
	private Property property;
	private Boolean delared;
	
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
		return delared;
	}
	
	public Boolean isDelayed() {
		return delared;
	}

	public void setDelayed(Boolean delayed) {
		this.delared = delayed;
	}
}
