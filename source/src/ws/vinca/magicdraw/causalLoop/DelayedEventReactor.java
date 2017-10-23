package ws.vinca.magicdraw.causalLoop;

import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;

public class DelayedEventReactor implements IEventReactor
{
	private static String DELAY_PREFIX = "||";
	private static int TARGET_END = 0;
	//private static int SOURCE_END = 1;

	@Override
	public void React(ICausalEvent e)
	{
		if( e instanceof DelayedEvent )
		{
			DelayedEvent de = (DelayedEvent) e;
			String multiplicity = ModelHelper.getMultiplicity( de.getLink().getOwnedEnd().get(TARGET_END) );
			if( de.isDelayed() )
			{
				if( !multiplicity.contains(DELAY_PREFIX) )
				{
					multiplicity = DELAY_PREFIX + (multiplicity.length() == 0 ? "" : " " ) + multiplicity;
				}
			}
			else
			{
				if( multiplicity.contains(DELAY_PREFIX) )
				{
					multiplicity = multiplicity.replaceAll( DELAY_PREFIX + " ", "" );
					multiplicity = multiplicity.replaceAll( DELAY_PREFIX, "" );						
				}
			}
			ModelHelper.setMultiplicity(multiplicity,
				de.getLink().getOwnedEnd().get(TARGET_END) );
		}
	}

}
