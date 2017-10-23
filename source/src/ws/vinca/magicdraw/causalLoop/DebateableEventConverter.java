package ws.vinca.magicdraw.causalLoop;

import java.util.List;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;

public class DebateableEventConverter extends CausalLinkConverter {
	@Override
	protected void ConvertCausalLinkPropertyEvent(Property propertyChanged, Association onAssociation, Object withValue,
			List<ICausalEvent> events) {
		if( propertyChanged.getName().equalsIgnoreCase("Debateable") )
		{
			DebateableEvent de = new DebateableEvent();
			de.setLink(onAssociation);
			de.setProperty(propertyChanged);
			de.setDebateable((Boolean)withValue);
			events.add(de);
		}
	}
}
