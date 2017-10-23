package ws.vinca.magicdraw.causalLoop;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.core.project.ProjectEventListenerAdapter;
import com.nomagic.magicdraw.plugins.Plugin;
import com.nomagic.uml2.transaction.TransactionManager;

public class PlugIn extends Plugin {

	private CausalTransactionListener mTransactionListener;
	
	@Override
	public boolean close() {
		return true;
	}

	@Override
	public void init() {
		mTransactionListener = new CausalTransactionListener();

		Application.getInstance().getProjectsManager().addProjectListener(new ProjectEventListenerAdapter()
		{
			@Override
			public void projectOpened(Project project)
			{
				TransactionManager transactionManager = project.getRepository().getTransactionManager();
				transactionManager.addTransactionCommitListener(mTransactionListener);
//				registerListenerToSmartEventSupport(project);
			}
			
			@Override
			public void projectClosed(Project project)
			{
				project.getRepository().getTransactionManager().removeTransactionCommitListener(mTransactionListener);
			}

		});
	}

	@Override
	public boolean isSupported() {
		//plugin can check here for specific conditions
		//if false is returned plugin is not loaded.
		return true;
	}

	/**
	 * Registers listener to {@link com.nomagic.uml2.ext.jmi.smartlistener.SmartEventSupport}, for Classifiers to receive
	 * events when it's owned attribute Static or Name property value are changed.
	 *
	 * @param project project where listener is registered.
	 */
//	private void registerListenerToSmartEventSupport(Project project)
//	{
//		//this config will listen for Owned attribute static and Name property value changes, for example: if Classifier has attribute, and this listener is registered to it, it will receive events if any of attribute name or static property is changed.
//		SmartListenerConfig cfg = new SmartListenerConfig();
//		SmartListenerConfig config = cfg.listenToNested(PropertyNames.OWNED_ATTRIBUTE);
//		config.listenTo(PropertyNames.IS_STATIC);
//		config.listenTo(PropertyNames.NAME);
//		List<SmartListenerConfig> configs = Collections.singletonList(cfg);
//		// register at event support.
//		project.getSmartEventSupport().registerConfig(Classifier.class, configs, new AnyPropertyChangeListener());
//	}
}
