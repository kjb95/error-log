## 커스텀 객체를 요소로 가지는 리스트에서 중복 제거하기

    Stream.distinct() : 스트림에서 중복되는 요소들을 모두 제거해주고 새로운 스트림을 반환

커스텀 클래스를 Stream.distinct()을 이용하여 중복되는 요소들을 제거하려 했으나 되지 않았음  
구글링 해보니, Stream.distinct()에서 동일한 객체인지 판단하는 기준은 Object.hashCode(), Object.equals() 라고 함  
따라서 커스텀 클래스에서 특정 요소만 같을때 동일 객체라고 판단하고 싶으면, Object.hashCode(), Object.equals()를 오버라이딩 해주어야 함

## Object.hashCode()

객체를 식별하는 Integer 값으로, 높은 비용이 드는 Object.equals()을 사용하기 전에 먼저 해시코드를 이용해 객체를 비교할때 드는 비용을 낮출 수 있음

    해시코드가 다르면, 두 객체는 다름
    해시코드가 같으면, equals()를 사용하여 객체 비교

## Object.equals(), Object.hashCode() 오버라이딩

    Object.equals() : 모든 요소가 같을때 true를 반환하도록 오버라이딩
    Object.hashCode() : 특정 해시코드가 나오는 요소가 유일하도록 오버라이딩

두 메소드를 모두 알맞게 오버라이딩을 해주니, Stream.distinct()을 이용하여 중복 요소를 제거할수 있게 됨