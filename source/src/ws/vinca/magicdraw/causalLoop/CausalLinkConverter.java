package ws.vinca.magicdraw.causalLoop;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;

public abstract class CausalLinkConverter implements IEventConverter {

	@Override
	public List<ICausalEvent> ConvertEvent(PropertyChangeEvent event) {
		List<ICausalEvent> events = new ArrayList<ICausalEvent>();
		Object source = event.getSource();
		if( source instanceof ValueSpecification && event.getNewValue() instanceof Boolean )
		{
			Object parameterContainer = ((ValueSpecification)source).getOwner();
			if( parameterContainer instanceof Slot )
			{
				Slot slot = (Slot)((ValueSpecification)source).getOwner();
				Element owner = slot.getOwningInstance().getStereotypedElement();
				if( owner instanceof Association )
				{
					Association association = (Association)owner;
					if( association.getHumanType().equalsIgnoreCase("Causal Link") )
					{
						Object propertyTest = slot.getDefiningFeature();
						if( propertyTest instanceof Property )
						{
							Property property = (Property)propertyTest;
							ConvertCausalLinkPropertyEvent(property,
								association, 
								event.getNewValue(), 
								events);
						}
					}
				}
			}
		}
		return events;
	}

	protected abstract void ConvertCausalLinkPropertyEvent(Property propertyChanged, 
			Association onAssociation,
			Object withValue,
			List<ICausalEvent> events);

}
