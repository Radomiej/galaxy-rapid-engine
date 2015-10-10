package galaxy.rapid.scene;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)  
public class SceneNavigatorTest {

	SceneNavigator sceneNavigator;
	TestOneScreen test1, test2;
	TestTwoScreen test3;
	
	@Before
	public void setUp() throws Exception {
		test1 = new TestOneScreen();
		test2 = new TestOneScreen();
		test3 = new TestTwoScreen();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testWithTheSameScreenClass() {		
		initialScreenIsCurrentScreen();		
		setScreenWithFalseDisposeGiveOldSceneForTheSameClassScreen();		
		setScreenWithTrueDisposeGiveNewScreenForTheSameClassScreen();		
	}
	
	@Test
	public void testWithDifrentScreenClass() {		
		initialScreenIsCurrentScreen();
		setScreenWithDifrentClassGiveNewOneOrNonDisposableOld();		
	}

	private void initialScreenIsCurrentScreen() {
		sceneNavigator = new SceneNavigator(test1);
		assertEquals(test1, sceneNavigator.getCurrentScene());
	}

	private void setScreenWithFalseDisposeGiveOldSceneForTheSameClassScreen() {
		assertEquals(test1.getClass(), test2.getClass());
		sceneNavigator.changeScene(test2, false);
		assertEquals(test1, sceneNavigator.getCurrentScene());
	}

	private void setScreenWithTrueDisposeGiveNewScreenForTheSameClassScreen() {
		sceneNavigator.changeScene(test2, true);
		assertEquals(test2, sceneNavigator.getCurrentScene());
	}

	private void setScreenWithDifrentClassGiveNewOneOrNonDisposableOld() {
		sceneNavigator.changeScene(test3, false);
		assertEquals(test3, sceneNavigator.getCurrentScene());
		
		TestOneScreen tempScreen = new TestOneScreen();
		sceneNavigator.changeScene(tempScreen, true);
		assertEquals(test1, sceneNavigator.getCurrentScene());
	}

}
