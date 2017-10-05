package ws.vinca.magicdraw.causalLoop;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;

public class OppositeEvent implements ICausalEvent {

	private Association link;
	private Property property;
	private Boolean oppositeEffect;
	
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
		return oppositeEffect;
	}
	
	public Boolean isOppositeEffect() {
		return oppositeEffect;
	}

	public void setOppositeEffect(Boolean oppositeEffect) {
		this.oppositeEffect = oppositeEffect;
	}
}
