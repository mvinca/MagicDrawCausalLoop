package ws.vinca.magicdraw.causalLoop;

public class QuickFixReactor implements IEventReactor
{

	private static String DEBATE_PREFIX = "QF - ";

	@Override
	public void React(ICausalEvent e)
	{
		if( e instanceof QuickFixEvent )
		{
			QuickFixEvent qf = (QuickFixEvent) e;
			if( qf.isQuickFix() )
			{
				if( qf.getLink().getName().length() == 0 )
				{
					qf.getLink().setName("QF");
				}
				else if( !qf.getLink().getName().equals("QF") )
				{
					qf.getLink().setName(DEBATE_PREFIX + qf.getLink().getName());
				}
			}
			else
			{
				if( qf.getLink().getName().startsWith(DEBATE_PREFIX) )
				{
					qf.getLink().setName(qf.getLink().getName().substring(DEBATE_PREFIX.length()));
				}
				else
				{
					qf.getLink().setName("");
				}
			}
		}
	}

}
