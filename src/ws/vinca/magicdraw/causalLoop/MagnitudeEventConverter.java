package ws.vinca.magicdraw.causalLoop;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;

public class MagnitudeEventConverter implements IEventConverter
{

	protected void ConvertCausalLinkPropertyEvent(Property propertyChanged, Association onAssociation, Object withValue,
		List<ICausalEvent> events)
	{
		JOptionPane.showMessageDialog(null, propertyChanged.getName());
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

		Object source = event.getSource();
		if( source instanceof EnumerationLiteral )
		{
			EnumerationLiteral enumValue = (EnumerationLiteral) source;
			JOptionPane.showMessageDialog(null, enumValue.getClassifier().get(0));
			JOptionPane.showMessageDialog(null, enumValue.getStereotypedElement() );
			JOptionPane.showMessageDialog(null, enumValue.getStereotypedElement().getClass() );
			Object parameterContainer = enumValue.getEnumeration().getOwner();
			if( parameterContainer instanceof Slot )
			{
				Slot slot = (Slot) (enumValue).getOwner();
				JOptionPane.showMessageDialog(null, slot);
				JOptionPane.showMessageDialog(null, slot.getOwningInstance().getStereotypedElement());
				Element owner = slot.getOwningInstance().getStereotypedElement();
				if( owner instanceof Association )
				{
					Association association = (Association) owner;
					if( association.getHumanType().equalsIgnoreCase("Causal Link") )
					{
						Object propertyTest = slot.getDefiningFeature();
						if( propertyTest instanceof Property )
						{
							Property property = (Property) propertyTest;
							ConvertCausalLinkPropertyEvent(property, association, event.getNewValue(), events);
						}
					}
				}
			}
		}
		return events;
	}
}
