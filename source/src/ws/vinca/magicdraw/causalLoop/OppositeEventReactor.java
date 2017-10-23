package ws.vinca.magicdraw.causalLoop;

import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

public class OppositeEventReactor implements IEventReactor {

	private static String OPP_PREFIX = "O - ";
	private static int TARGET_END = 0;
	//private static int SOURCE_END = 1;
	
	@Override
	public void React(ICausalEvent e) 
	{
		if( e instanceof OppositeEvent )
		{
			OppositeEvent oe = (OppositeEvent)e;
			String multiplicity = (Boolean)StereotypesHelper.getStereotypePropertyFirst(oe.getLink(), "Causal Link", "Delay") ?
				"|| " : "";
			if( oe.isOppositeEffect() )
			{
				if( oe.getLink().getName().length() == 0 )
				{
					oe.getLink().setName( "O" );
				}
				else if( !oe.getLink().getName().equals("O") )
				{
					oe.getLink().setName(OPP_PREFIX + oe.getLink().getName() );
				}
				multiplicity += "-";
			}
			else
			{
				if( oe.getLink().getName().startsWith(OPP_PREFIX) )
				{
					oe.getLink().setName(oe.getLink().getName().substring(OPP_PREFIX.length()));
				}
				else
				{
					oe.getLink().setName( "" );
				}
				multiplicity += "+";
			}
			ModelHelper.setMultiplicity(multiplicity,
				oe.getLink().getOwnedEnd().get(TARGET_END) );
		}
	}
}
