package ws.vinca.magicdraw.causalLoop;

import java.util.List;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;

public class QuickFixEventConverter extends CausalLinkConverter
{
	@Override
	protected void ConvertCausalLinkPropertyEvent(Property propertyChanged, Association onAssociation, Object withValue,
		List<ICausalEvent> events)
	{
		if( propertyChanged.getName().equalsIgnoreCase("QuickFix") )
		{
			QuickFixEvent qf = new QuickFixEvent();
			qf.setLink(onAssociation);
			qf.setProperty(propertyChanged);
			qf.setQuickFix((Boolean) withValue);
			events.add(qf);
		}
	}
}
