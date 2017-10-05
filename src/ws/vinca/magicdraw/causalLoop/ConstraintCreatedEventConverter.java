package ws.vinca.magicdraw.causalLoop;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import com.nomagic.uml2.ext.jmi.UML2MetamodelConstants;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;

public class ConstraintCreatedEventConverter implements IEventConverter
{
	@Override
	public List<ICausalEvent> ConvertEvent(PropertyChangeEvent event)
	{
		List<ICausalEvent> events = new ArrayList<ICausalEvent>();
		if( UML2MetamodelConstants.INSTANCE_CREATED.equals(event.getPropertyName()) )
		{
			if( event.getSource() instanceof Association )
			{
				Association association = (Association) event.getSource();
				if( association.getHumanType().equalsIgnoreCase("Constraint") )
				{
					ConstraintCreatedEvent cce = new ConstraintCreatedEvent();
					cce.setLink(association);
					events.add(cce);
				}
			}
		}
		return events;
	}
}
