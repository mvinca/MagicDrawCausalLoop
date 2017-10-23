package ws.vinca.magicdraw.causalLoop;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;

public interface ICausalEvent 
{
	Association getLink();
	Property getProperty();
	Object getValue();
}
