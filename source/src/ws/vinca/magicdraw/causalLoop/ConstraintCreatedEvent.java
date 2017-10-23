package ws.vinca.magicdraw.causalLoop;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;

public class ConstraintCreatedEvent implements ICausalEvent
{
	private Association link;
	
	@Override
	public Association getLink()
	{
		return link;
	}

	public void setLink(Association link)
	{
		this.link = link;
	}

	@Override
	public Property getProperty()
	{
		return link.getAttribute().stream().filter( p -> p.getName().equalsIgnoreCase( "Name" ))
			.findAny().get();
	}

	@Override
	public Object getValue()
	{
		return link.getName();
	}

}
