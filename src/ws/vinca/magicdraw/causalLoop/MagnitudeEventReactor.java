package ws.vinca.magicdraw.causalLoop;

import javax.swing.JOptionPane;

import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

public class MagnitudeEventReactor implements IEventReactor
{
	@Override
	public void React(ICausalEvent e)
	{
		if( e instanceof MagnitudeEvent )
		{
			JOptionPane.showMessageDialog(null, e.getValue().getClass() );
			JOptionPane.showMessageDialog(null, e.getValue() );
		}
	}
}
