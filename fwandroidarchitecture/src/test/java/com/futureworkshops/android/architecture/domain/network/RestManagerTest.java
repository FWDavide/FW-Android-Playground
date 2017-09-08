package com.futureworkshops.android.architecture.domain.network;


import com.futureworkshops.android.architecture.domain.rx.scheduler.SchedulersProvider;
import com.futureworkshops.android.architecture.model.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RestManagerTest {

    @Mock
    private RestApi restApi;

    private RestManager restManager;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        restManager = new RestManager(restApi, new TestSchedulerProvider());
    }

    // Maybe we can stick with this format for naming tests (methodUnderTest_useCase)
    @Test
    public void login_verifiesThatTheNormalFlowWorksCorrectly() {
        User user = mock(User.class);
        Single<User> userSingle = Single.just(user);
        when(restApi.login(anyString(), anyMapOf(String.class, String.class))).thenReturn(userSingle);

        TestObserver<User> testObserver = new TestObserver<>();
        restManager.login(anyString(), anyString()).subscribe(testObserver);

        testObserver.assertSubscribed();
        testObserver.assertNoErrors();

        // if the functions called by the chain change the original response, the new one should be
        // verified by using onSuccessEvents, this is what the observer receives in it's onNext/onSuccess method.
        List<Object> onSuccessEvents = testObserver.getEvents().get(0);
        assertTrue(onSuccessEvents.get(0).equals(user));
        List<Object> onErrorEvents = testObserver.getEvents().get(1);
        assertTrue(onErrorEvents.isEmpty());
        List<Object> onCompleteEvents = testObserver.getEvents().get(2);
        assertTrue(onCompleteEvents.size() == 1);

        verify(user).getName();
        verify(user).getAddress();
        verify(user).getEmail();
    }

    public static class TestSchedulerProvider implements SchedulersProvider {

        @Override
        public Scheduler io() {
            return Schedulers.trampoline();
        }

        @Override
        public Scheduler ui() {
            return Schedulers.trampoline();
        }
    }
}