zest-andoid
===========

<img src="https://raw.github.com/stephanenicolas/zest-android/master/gfx/logo.png" 
width="350px" />

Zest android is a very specialized library to write Android tests more easily when using RoboGuice and/or EasyMock.

A zest test has two sides : 
* it will receive all injections of the class under tests, simply give the tests the same fields as object under tests.
* all its fields annotated with @Mock will receive mocks and the mocks will be injected in object under tests via RoboGuice.

Here is an example  :

````java

/**
 * This class is a unit test of MainActivity. It will mock all dependencies of {@link MainActivity}.
 * Only {@link MainActivity} will be tested, not computers. <br>
 * It will use a custom RoboGuice module to bind the mocked dependencies to RoboGuice so that mocks are injected automatically inside class under test.
 * It will receive the same injections as Activity under test if fields are declared in the same way as in this activity (same name, type and annotation).
 * 
 * @author SNI
 */
public class MainActivityUnitTest extends EasyMockRoboActivityInstrumentationTestCase2<MainActivity> {

	private static final int TEST_COMPUTE_RESULT = 44;

	@InjectView(R.id.button_main)
	private Button buttonMain;

	@InjectView(R.id.button_next)
	private Button buttonNext;

	@InjectView(R.id.textview_main)
	private TextView textViewMain;

	@Mock
	public Computer mockComputer;
	@Mock
	public Computer2 mockComputerSingleton;

	public MainActivityUnitTest() {
		super(MainActivity.class);
	}

	@UiThreadTest
	public void testComputeReturnsGoodAnswer() {
		// given
		EasyMock.expect(mockComputer.compute()).andReturn(TEST_COMPUTE_RESULT);
		EasyMock.expect(mockComputerSingleton.compute()).andReturn(TEST_COMPUTE_RESULT);

		replayAllMocks();

		// when
		buttonMain.performClick();

		// test
		assertThat(textViewMain).containsText(String.valueOf(TEST_COMPUTE_RESULT));
		verifyAllMocks();
	}
}

````
