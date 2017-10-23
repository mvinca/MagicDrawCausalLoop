package ws.vinca.magicdraw.causalLoop;

import java.util.List;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;

public class OppositeEventConverter extends CausalLinkConverter {
	@Override
	protected void ConvertCausalLinkPropertyEvent(Property propertyChanged, Association onAssociation, Object withValue,
			List<ICausalEvent> events) {
		if( propertyChanged.getName().equalsIgnoreCase("OppositeEffect") )
		{
			OppositeEvent oe = new OppositeEvent();
			oe.setLink(onAssociation);
			oe.setProperty(propertyChanged);
			oe.setOppositeEffect((Boolean)withValue);
			events.add(oe);
		}
	}
}
