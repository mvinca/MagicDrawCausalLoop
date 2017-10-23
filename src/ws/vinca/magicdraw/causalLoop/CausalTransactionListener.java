package ws.vinca.magicdraw.causalLoop;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.nomagic.uml2.transaction.TransactionCommitListener;

public class CausalTransactionListener implements TransactionCommitListener
{

	private List<IEventConverter> converters;
	private List<IEventReactor> reactors;

	public CausalTransactionListener()
	{
		IEventConverter[] array = { new OppositeEventConverter(), new DebateableEventConverter(),
			new QuickFixEventConverter(), new ConstraintCreatedEventConverter(), new DelayedEventConverter(),
			new MagnitudeEventConverter() };
		converters = Arrays.asList(array);
		IEventReactor[] array2 = { new OppositeEventReactor(), new DebateableEventReactor(), new QuickFixReactor(),
			new ConstraintCreatedEventReactor(), new DelayedEventReactor(), new MagnitudeEventReactor() };
		reactors = Arrays.asList(array2);
	}

	@Override
	public Runnable transactionCommited(Collection<PropertyChangeEvent> events)
	{
		return new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					ArrayList<ICausalEvent> causalEvents = new ArrayList<ICausalEvent>();
					for( PropertyChangeEvent event : events )
					{
						converters.stream().map(conv -> conv.ConvertEvent(event)).forEach(list -> causalEvents.addAll(list));
					}
					for( ICausalEvent ce : causalEvents )
					{
						for( IEventReactor er : reactors )
						{
							er.React(ce);
						}
					}
				}
				catch( Exception ex )
				{
					javax.swing.JOptionPane.showMessageDialog(null, "Exception: " + ex.getMessage());
				}
			}

		};
	}

}
