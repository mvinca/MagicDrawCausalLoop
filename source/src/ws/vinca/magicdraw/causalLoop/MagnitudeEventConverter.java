package ws.vinca.magicdraw.causalLoop;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot;

public class MagnitudeEventConverter implements IEventConverter
{

	protected void ConvertCausalLinkPropertyEvent(Property propertyChanged, Association onAssociation, EnumerationLiteral withValue,
		List<ICausalEvent> events)
	{
		if( propertyChanged.getName().equalsIgnoreCase("Magnitude") )
		{
			MagnitudeEvent me = new MagnitudeEvent();
			me.setLink(onAssociation);
			me.setProperty(propertyChanged);
			me.setValue(withValue);
			events.add(me);
		}
	}

	@Override
	public List<ICausalEvent> ConvertEvent(PropertyChangeEvent event)
	{
		List<ICausalEvent> events = new ArrayList<ICausalEvent>();

		Object source = event.getNewValue();
		if( source instanceof InstanceValue )
		{
			InstanceValue enumValue = (InstanceValue) source;
			//JOptionPane.showMessageDialog(null, enumValue );
			Slot slot = (Slot) enumValue.getOwningSlot();
			//JOptionPane.showMessageDialog(null, slot );
			Element owner = slot.getOwningInstance().getStereotypedElement();
			//JOptionPane.showMessageDialog(null, owner );
			if( owner instanceof Association )
			{
				Association association = (Association) owner;
				if( association.getHumanType().equalsIgnoreCase("Causal Link") )
				{
					Object propertyTest = slot.getDefiningFeature();
					if( propertyTest instanceof Property )
					{
						Property property = (Property) propertyTest;
						ConvertCausalLinkPropertyEvent(property, association, (EnumerationLiteral)enumValue.getInstance(), events);
					}
				}
			}
		}
		return events;
	}
}
