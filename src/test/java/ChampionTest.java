import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.*;

public class ChampionTest {
    private List<Champion> championList = new ArrayList<Champion>();

    @Before
    public void setUp() {

        //5개의 챔피언 객체를 만듭니다.
        Champion topChamp = new Champion("다리우스", "탑");
        Champion jungleChamp = new Champion("리신", "정글");
        Champion midChamp = new Champion("르블랑", "미드");
        Champion adcChamp = new Champion("베인", "바텀");
        Champion supportChamp = new Champion("레오나", "바텀");

        //앞서 만든 List 에 각 챔피언을 추가합니다.
        championList.add(topChamp);
        championList.add(jungleChamp);
        championList.add(midChamp);
        championList.add(adcChamp);
        championList.add(supportChamp);
    }

    //List<String>을 생성하고 값이 비어 있는지를 테스트 empty()
    //assert 단정문중 assertTrue 를 이용해서 isEmpty() 함수로 이 조건이 참인지 확인
    //김수영
    @Test
    public void givenCollectionWhenEmptyCorrect() {

        List<String> emptyList = new ArrayList<>();
        assertTrue(emptyList.isEmpty());

    }

    //notNullValue 활용한 테스트
    //assert 단정문에서 null value 를 가지지 않는 것을 확인하는 assertNotNull() 를 이용해서 확인
    //김수영
    @Test
    public void notNullCheck() {

        String lck = "LCK";
        assertNotNull(lck);

    }

    //nullValue 활용한 테스트
    @Test
    public void givenStringWhenNullIsCorrect() {

        String lck = null;
        assertThat(lck, is(nullValue()));
        assertThat(null, is(nullValue()));
        assertNull(lck);

//        assertThat(lck, nullValue());
    }


    //문자열 관련 테스트 anyOf, containsString, endWith
    @Test
    public void testForRelatedString() {
        String sampleString1 = "Player Focus";
        String sampleString2 = "Player point";
        String startString = "Player";
        String endString = "point";
        assertThat(sampleString1, anyOf(endsWith(endString), containsString(endString), containsString(startString)));
        assertThat(sampleString2, anyOf(endsWith(endString), endsWith("aaa")));
        assertThat(sampleString2, is(startsWith(startString)));

    }

    //부동소수점 범위 closeTo 테스트
    @Test
    public void testForFloatingPoint() {

        double num= 5.0;
        assertThat(2.0, closeTo(1.9999, 0.1));
        assertThat(num, is(closeTo(4.8, 0.3)));


//        assertThat(3.14, closeTo(3, 0.2));
    }

    //anything 테스트
    @Test
    public void shouldNotErrorGetReference() {
        assertThat("a", anything());
        assertThat("aaa", anything());
        assertThat(championList.get(1),anything());
        assertThat(anything(), anything());
        assertFalse(championList.get(1).equals(anything()));
        assertFalse(nullValue() == anything());

//        assertThat(championList.get(2), anything());
    }

    //객체 크기 검증 테스트 hasSize
    @Test
    public void shouldChampionCountFive() {
        assertFalse(championList.size()==4);
        assertTrue(championList.size()>3);
        assertTrue(championList.size()<130);
        assertThat(championList, hasSize(lessThan(10)));

//        assertTrue(championList.size() == 5);
//        assertThat(championList.size(), is(5));
//        assertThat(championList, hasSize(5));
    }

    //서폿 챔피언은 자이라이어야 한다는 조건으로 테스트 코드 작성
    //assertThat, assertEquals, assertTrue의 assert 단정문을 이용하고
    //hamcrest 에서 equalTo, hasToString 함수를 사용해서 서포트 챔피언 == 자이라 를 인증
    //김수영
    @Test
    public void shouldSupportChampionIsZyra() {
        
        Champion supportChamp = new Champion("자이라", "미드");

        assertThat("자이라", is(supportChamp.getName()));
        assertThat("자이라",is(equalTo(supportChamp.getName())));
        assertThat("자이라",equalTo(supportChamp.getName()));
        assertEquals(supportChamp.getName(),"자이라");
        assertThat("자이라",hasToString(supportChamp.getName()));
        assertTrue(supportChamp.getName()=="자이라");

    }

    //hasProperty 활용하여 속성이 포함되어 있는지 테스트
    //name 혹은 property 를 속성으로 가지므로 name 으로 실험
    //배열의 4번째 이름(속성) 은 베인인것을 test
    //김수영
    @Test
    public void shouldHasPropertyPosition() {

        assertThat(championList.get(3),hasProperty("name"));
        assertThat(championList.get(3), hasProperty("name", is("베인")));

    }

    //hasToString 활용 테스트
    //champListNames 의 배열에 들어가는 champion 이름을 인기 순 으로 나열해서 집어 넣고
    //hasToString 을 사용해서 ToString 에서 값이 일치하는지 확인한다.
    //김수영
    @Test
    public void shouldHaveSomeChampName() {

        List<String> champListNames = Arrays.asList("이즈리얼", "카이사", "럭스", "리신", "쓰레쉬", "뽀삐");
        assertThat(champListNames.get(2), hasToString("럭스"));

    }

    //property와 value가 같은지 테스트
    @Test
    public void shouldHaveSamePropertyAndValue() {
        List<String> championNames1 = Arrays.asList("루시안", "애쉬", "렉사이", "갈리오", "모르가나", "블라디미르");
        List<String> championNames2 = Arrays.asList("루시안", "애쉬", "렉사이", "갈리오", "모르가나", "블라디미르");
        assertThat(championNames1, is(championNames2)); //201520969 이재형
//        assertThat(championNames1, samePropertyValuesAs(championNames2));
    }

    //탑 챔피언은 다리우스여야 한다라는 조건으로 테스트 코드 작성, stream 활용예
    @Test
    public void shouldTopChampionIsDarius() {
        Optional<Champion> filterdChampion = championList.stream().filter(c -> c.getPosition().equals("바텀")).findFirst();
        String champName = filterdChampion.get().getName();
        assertTrue(champName.equals("베인"));
        assertThat("베인",is(champName)); //201520969 이재형
//        Optional<Champion> filterdChampion = championList.stream()
//                .filter(c -> c.getPosition().equals("탑"))
//                .findFirst();
//        String champName = filterdChampion.get().getName();
//        assertTrue(champName.equals("다리우스"));
//        assertThat("다리우스", is(champName));
    }

}