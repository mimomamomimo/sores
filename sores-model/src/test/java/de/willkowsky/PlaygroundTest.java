package de.willkowsky;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class PlaygroundTest {

    @Mock
    private BruteForceStrategy bruteForceStrategy;

    @Mock
    private PlanBStrategy planBStrategy;

    @InjectMocks
    private Playground playground;

    @Test
    public void testConstructor() throws Exception {
        if(false) {
            Playground playground = null;
            initMocks(this);
            playground = mock(Playground.class);
            Whitebox.setInternalState(playground, "strategy", bruteForceStrategy);
            when(bruteForceStrategy.resolve(any())).thenReturn(true);
        }
        playground.resolve(bruteForceStrategy);
        verify(bruteForceStrategy, times(1)).resolve(any(Playground.class));
    }
}