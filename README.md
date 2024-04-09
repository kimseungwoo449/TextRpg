# 🙋‍♂Study-Java
--------------
## 🐟Text_Rpg

간단한 뽑기형식의 RPG 게임이다.

사용자는 가입 시 직업군이 서로 다른 4명의 1성영웅과 10000캐쉬를 지급받는다.

로그 인 후 여러 기능들을 사용할 수 있다.

저장, 로드를 통해 본인의 사용 데이터가 저장된다.

----------------------
## 📌1. 개발환경
* **개발언어 : JAVA**
* **개발환경 : JavaSE-11**

-----------------------
## 📌2. 동작 설명

🐬 처음 시작은 **타이틀 스테이지**에서 시작한다.

----------------------------------------
### 🐬타이틀 스테이지의 기능
유저의 회원가입, 로그 인 등 유저 정보를 주로 다룬다.
----------------------------------------

1. 회원가입
  * **로그 아웃** 시에만 이용가능하다.
  * 유저의 **가입**을 진행한다.
  * 사용할 **아이디**와 **패스워드**를 입력한다.
  * 만약, 이미 등록되어있는 아이디라면 오류메세지 출력 후 대기화면으로 돌아간다.
  * 회원가입이 완료된다면, **10000캐쉬**와 직업군이 다른 **4명의 1성영웅**을 지급한다.

2. 회원탈퇴
  * **로그 인** 시에만 이용가능하다.
  * 유저의 **탈퇴**를 진행한다.
  * **비밀번호**를 입력받는다.
  * 현재 로그인 된 유저의 비밀번호와, 입력받은 비밀번호를 비교한다.
  * 만약, 서로 다르다면 오류메세지 출력 후 대기화면으로 돌아간다.
  * 같다면, **탈퇴**를 완료한다.

3. 회원정보수정
  * **로그 인** 시에만 이용가능하다.
  * 유저의 **비밀번호수정**을 진행한다.
   * **현재 비밀번호**와 **수정할 비밀번호**를 입력받는다.
  * 현재 로그인 된 유저의 비밀번호와, 입력받은 **현재 비밀번호**를 비교한다.
  * 만약, 서로 다르다면 오류메세지 출력 후 대기화면으로 돌아간다.
  * 같다면, 유저의 비밀번호를 **수정할 비밀번호**로 업데이트한다.
  * 이후, **로그 아웃**상태로 돌아간다.

4. 로그 인
  * **로그 아웃** 시에만 이용가능하다.
  * **로그 인**을 진행한다.
  * **아이디**와 **패스워드**를 진행한다.
  * 모두 일치하는 유저가 존재하면 **static으로 선언된 로그 변수**를 해당 유저의 **인덱스**로 수정한다.
  * 만약, 존재하지 않는다면 오류메세지를 출력 한다.

5. 로그 아웃
  * **로그 인** 시에만 이용가능한다.
  * **static으로 선언된 로그 변수**를 -1로 수정한다.

6. 로비
  * **로그 인** 시에만 이용가능하다.
  * **로비 스테이지**로 이동한다.
  * **로비 스테이지**의 기능은 후술한다.

7. 저장
  * 회원 가입된 모든 회원들의 정보를 **저장** 한다.
  * **저장**된 정보들은 **.txt** 파일로 저장된다.

0. 종료
  * 프로그램을 **종료**한다.
-----------------------------------
### 🐬 **로비 스테이지**의 기능

**static으로 선언된 로그 변수**를 기반으로 유저를 특정하여 기능들을 수행한다.
-----------------------------------
1. 전투
  * **전투**를 진행한다.
  * **파티생성**후 진행가능하다.
  * **파티의 인덱스**를 입력받고 진행한다.
  * **파티 인덱스**를 기반으로 파티를 특정하고, **몬스터 무리**를 랜덤 생성하여 **배틀 스테이지**에 진입한다.
  * **배틀 스테이지**의 기능은 후술한다.

2. 영웅뽑기
  * **영웅뽑기**를 진행한다.
  * 유저의 **캐쉬잔액**을 확인한다.
  * **캐쉬잔액**이 500원 미만이면 진행할 수 없다.
  * 직업군과 등급이 랜덤한 영웅을 **생성**한다.
  * 등급의 확률은 1성 50%, 2성 30%, 3성 20% 이다.
  * 직업군은 4개이므로 각각 25%의 확률이다.
  * 생성한 영웅은 유저의 myHero배열에 저장된다.
  * 유저의 **캐쉬잔액**은 500원 **차감**된다.

3. 영웅판매
  * **영웅판매**를 진행한다.
  * 유저의 myHero배열의 영웅들을 인덱싱하여 보여준다.
  * 인덱스를 입력받고 해당 영웅을 myHero배열에서 **삭제**한다.
  * **영웅등급**에 따라 받을 수 있는 캐쉬가 다르다.
  * 받을 수 있는 캐쉬는 **300*영웅등급**원 이다.

4. 파티생성
  * **파티생성**을 진행한다.
  * 파티의 인원은 **총 3명**이다.
  * 인덱스를 3번 입력받는다.
  * 입력받은 인덱스는 배열에 저장된다.
  * 만약, **인덱스범주**를 벗어 나거나, **동일한 영웅**이 선택된다면 **다시** 선택한다.
  * 배열을 기반으로 파티를 조직해, 유저의 parties 맵에 **저장**한다.
  * 맵의 Key는 유저의 partyNumber 변수로 인덱싱 된다.

5. 파티해체
  * **파티해체**를 진행한다.
  * 현재 유저가 지닌 parties 맵을 출력한다.
  * **Key값**, 즉 인덱스를 입력 받는다.
  * 만약 존재하지 않는 Key값이면 로비로 돌아간다.
  * 해당 Key값 보다 높은 Key를 가지는 party들은 앞으로 복사된다.
  * 맨 마지막 party를 삭제한다.
  * 유저의 **partyNumber**은 하나 차감된다.

6. 상점
  * **상점 스테이지**로 진입한다.
  * 상점의 주요 기능은 후술한다.

7. 영웅 보기
  * 유저가 지닌 **myHero** 배열을 출력한다.
  * 영웅들의 현 상태를 볼 수 있다.

8. 파티 보기
  * 유저가 지닌 **parties** 맵을 출력한다.
  * 파티목록과 파티 내부 영웅들의 현 상태를 볼 수 있다.

9. 인벤토리
  * 유저가 지닌 **consumableItem** 배열과 **equipableItem** 배열을 보거나 조작할 수 있다.
  * 소비아이템의 현황을 볼 수 있다.
  * 장비아이템의 현황을 보거나 착용, 해제 할 수있다.
  * 착용시에 **장비아이템의 인덱스**와 **영웅의 인덱스**를 입력 받는다.
  * 해당 영웅이 **해당 속성(무기, 방어구)의 장비를 장착 중**일 시, 착용 할 수없다.
  * 해제 시 **아이템 인덱스를 입력**받아, 해제를 진행한다.

0. 타이틀
  * **타이틀 스테이지**로 돌아간다.
----------------------------------
### 🐬 **배틀 스테이지**의 기능
파티와 몬스터 무리를 입력받아 생성하고, 전투를 주로 수행한다.
-----------------------------------
1. 현 상황 출력
  * 파티의 내부 스테이터스와 몬스터들의 내부 스테이터스를 출력한다.
  * 레벨, 등급, 직업군, 현 체력, 최대 체력들을 출력한다.

2. 플레이어 행동 입력
  * **공격, 스킬, 아이템**중 무엇을 할 지 입력한다.
  * 파티원 수만큼의 행동을 입력받는다.
  * **공격**은 해당 파티원의 **공격력+추가공격력**(이하 공격력으로 통합) 만큼 랜덤한 몬스터를 공격한다.
    * **추가 공격력**은 해당 영웅이 착용한 무기의 공격력이다.
  * **스킬**은 영웅의 직군별로 행동을 달리한다.
    * **전사** 직업군은 다음 턴에 **공격력의 세배**로 공격할 수 있게 준비한다.
    * **마법사** 직업군은 **파이어볼**을 사용해 **공격력의 1.5배**로 랜덤한 몬스터를 공격한다.
    * **성기사** 직업군은 **공격력의 세배**로 자기자신을 회복한다.
    * **성직자** 직업군은 **공격력의 네배**로 랜덤한 파티원을 회복한다.
  * **아이템**은 **소모아이템**을 선택하여 사용한다.
    * **폭탄**을 선택하면 해당 폭탄의 공격력으로 랜덤한 몬스터를 공격한다.
    * **포션**을 선택하면 해당 영웅을 포션의 회복량으로 회복시킨다.
    * 아이템을 사용한 이후엔 유저의 인벤토리에서 아이템이 **삭제**된다.

3. 몬스터의 행동
  * 몬스터들의 **공격**과 **스킬**을 랜덤으로 사용한다.
  * 몬스터의 수만큼 행동이 반복된다.
  * **공격**시엔 해당 몬스터의 공격력으로 랜덤한 파티원을 공격한다.
    * 파티원의 **방어력**에 따라 공격이 **반감**된다.
  * **스킬**시엔 몬스터 고유의 스킬을 사용한다.
    * **박쥐**의 경우 랜덤한 파티원을 **공격력의 1.2배**로 공격 후, **해당 공격의 2배**로 자기 자신을 회복한다.
    * **오크**의 경우 랜덤한 파티원을 **공격력의 2배**로 공격한다.
    * **슬라임**의 경우 **공격력의 3배**로 자신을 회복한다.
    * **늑대**의 경우 랜덤한 파티원을 **공격력의 1.5배**로 공격한다.
   
4. 이 후
  * **파티원들** 혹은 **몬스터들**이 모두 죽었는지 판별하고 위를 반복한다.
  * 만약, **파티원들**이 모두 죽었으면 전투상황이 종료되고, **전투실패메세지**를 출력 후 로비로 돌아간다.
  * 만약, **몬스터들**이 모두 죽었으면 전투상황이 종료되고, 정산을 시작한다.
    * 몬스터들의 경험치를 모두 더해 파티원들의 경험치를 추가해준다.
      * 각 파티원 별로 최대경험치를 초과하면 레벨업을 진행한다.
      * 레벨업 시엔 해당 영웅의 최대경험치가 **1.2배** 증가한다.
      * 각 능력치들이 **1.3배**증가한다.
----------------------------------
### 🐬 **상점 스테이지**의 기능
소비아이템과 장비아이템을 장바구니에 담아 구매한다.
-----------------------------------
1. 유저의 소지금 출력
  * 유저의 소지금을 받아와 출력한다.

2. 안내 목록 출력
  * 소비아이템을 구매할 것인지, 장비아이템을 구매할 것인지, 장바구니에서 아이템을 제거할 것인지, 장바구니를 구매할 것인지 선택한다.

3. 소비 아이템
  * 폭탄, 포션을 구매할 수 있다.
  * 폭탄 구매시 장바구니에 폭탄을 추가한다.
    * 각 폭탄은 데미지가 랜덤하다.
  * 포션 구매시 장바구니에 포션을 추가한다.
    * 각 포션은 회복량이 랜덤하다.

4. 장비 아이템
  * 무기 또는 방어구를 구매할 수 있다.
  * 장비아이템은 등급별로 추가공격력, 방어력이 상이하다.
  * 등급별로 랜덤으로 추가공격력 또는 방어력을 갖는다.

5. 장바구니에서 빼기
  * 장바구니 목록을 출력한다.
  * 인덱스를 입력받아 아이템을 제거한다.

6. 구매
  * 장바구니내의 아이템들을 구매할 수 있다.
  * 장바구니내의 아이템들의 합산 가격을 보여주고 구매를 진행한다.
  * 만약 유저의 **캐쉬잔액**이 합산 가격보다 적다면 구매가 불가능 하다.
  * 구매 후엔 로비로 돌아간다.

-----------------------
## 📌3. Class Diagram

🐬 클래스들의 주요 상속관계를 보여주는 Diagram이다.
-------------------------------------------------------
1. 아이템 클래스의 상속관계

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/textRpg_Item.jpg)

* Item클래스는 추상클래스로 fucntion이라는 추상 메서드를 갖는다.
* Consumable과 Equipable이란 인터페이스가 존재한다.
* Equipable은 setNameAndPrice, setEquipedHeroIndex, getEquipedHeroIndex라는 추상메서드를 갖는다.
* 자식클래스로 ItemBomb, ItemPotion은 Consumable 인터페이스로 마킹한다.
* ItemArmor와 ItemWeapon은 Equipable 인터페이스로 마킹 및 메서드를 강제한다.

-----------------------------------------
2. 스테이지 클래스의 상속관계

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/textRpg_Stage.jpg)

* Stage클래스는 추상클래스로 printMenu라는 추상메서드를 지닌다.
* 또한 생성자로 stageName 변수 초기화를 강제한다.
* 자식 클래스로 StageTitle, StageLobby, StageStore, StageBattle 클래스가 존재한다.

-------------------------------------------
3. 유닛 클래스의 상속관계

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/textRpg_Unit.jpg)

* Unit클래스는 추상클래스로 skill이라는 추상메서드를 지닌다.
* 유닛은 속성(최대체력, 현 체력, 공격력, 이름, 경험치 등)을 지닌다.
* 자식클래스로 Monster, Hero가 존재한다.
* Monster의 자식클래스는 MonsterBat, MonsterOrc, MonsterSlime, MonsterWolf가 존재한다.
* Hero의 자식클래스는 HeroWarrior, HeroPrist, HeroPaladin, HeroWizard가 존재한다.

-----------------------------------
4. 유저 클래스와 유저매니저 클래스

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/textRpg_User%2CUserManager.jpg)

* User클래스는 각 유저의 속성(ID, Password, Cash,myHero 등)을 지닌다.
* UserManager클래스는 SingleTone으로 선언되어 **static으로 선언된 로그 변수**를 기반으로 여러 기능들을 수행한다.

--------------------------------------
5. 유닛 매니저 클래스

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/textRpg_UnitManager.jpg)

* 몬스터 무리 생성, 영웅 뽑기 기능을 수행한다.

--------------------------------------
6. 컬러 클래스

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/textRpg_Color.jpg)

* Color클래스는 멤버 변수 및 멤버 메소드들이 **static**으로 선언되어 출력문에 색을 입힌다.

-------------------------------------
7. 파일 매니저 클래스

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/textRpg_FileManager.jpg)

* 문자열을 입력받아 저장하는 save 메소드와 파일의 문자열을 불러와 반환하는 load메서드가 있다.
* SingleTone으로 선언되었다.

-----------------------
## 📌4. Flow Chart

🐬 주요 기능들의 흐름도 이다.
-------------------------------------------------------

1. 상점 기능의 FlowChart

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/textRpgFlowChart_%EC%83%81%EC%A0%90.jpg)

------------------------------------------------------

2. 영웅 뽑기 기능의 FlowChart

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/textRpgFlowChart_%EC%98%81%EC%9B%85%EB%BD%91%EA%B8%B0.jpg)

---------------------------------------------------

3. 영웅 판매 기능의 FlowChart

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/textRpgFlowChart_%EC%98%81%EC%9B%85%ED%8C%90%EB%A7%A4.jpg)


---------------------------------

4. 인벤토리 기능의 FlowChart

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/textRpgFlowChart_%EC%9D%B8%EB%B2%A4%ED%86%A0%EB%A6%AC.jpg)

-------------------------

5. 전투 기능의 FlowChart

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/textRpgFlowChart_%EC%A0%84%ED%88%AC.jpg)

--------------------------

6. 파티생성 기능의 FlowChart

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/textRpgFlowChart_%ED%8C%8C%ED%8B%B0%EC%83%9D%EC%84%B1.jpg)

------------------------

7. 파티해체 기능의 FlowChart

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/textRpgFlowChart_%ED%8C%8C%ED%8B%B0%ED%95%B4%EC%B2%B4.jpg)

------------------------

8. 회원가입 기능의 FlowChart

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/textRpgFlowChart_%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85.jpg)

-----------------------
## 📌4. Demo

🐬 주요 기능들의 Demo 이다.
-------------------------------------------------------

-------------------------------------------------------

1. 상점 및 장비아이템 착용 및 해제

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/%EC%83%81%EC%A0%90-%EB%B0%8F-%EC%9E%A5%EB%B9%84%EC%95%84%EC%9D%B4%ED%85%9C-%EC%B0%A9%EC%9A%A9_%ED%95%B4%EC%A0%9C.gif)

------------------------------------------------------

2. 영웅 뽑기 및 파티생성 및 해제

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/%EC%98%81%EC%9B%85%EB%BD%91%EA%B8%B0-%EB%B0%8F-%ED%8C%8C%ED%8B%B0-%EC%83%9D%EC%84%B1-%ED%95%B4%EC%A0%9C.gif)

---------------------------------------------------

3. 영웅 판매 기능(해당 영웅 파티제명, 착용중인 아이템 해제)

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/%EC%98%81%EC%9B%85%ED%8C%90%EB%A7%A4-%ED%8C%90%EB%A7%A4%EC%8B%9C-%EB%8F%88%EC%A3%BC%EA%B3%A0_-%ED%8C%8C%ED%8B%B0%EC%97%90%EC%84%9C-%EB%B9%BC%EA%B3%A0_-%EC%B0%A9%EC%9A%A9%EC%A4%91%EC%9D%B4-%EC%95%84%EC%9D%B4%ED%85%9C-%ED%95%B4%EC%A0%9C.gif)


---------------------------------

4. 저장 및 로드

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/%EC%A0%80%EC%9E%A5-%EB%B0%8F-%EB%A1%9C%EB%93%9C.gif)

-------------------------

5. 전투

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/%EC%A0%84%ED%88%AC.gif)

--------------------------

6. 회원가입 및 로그인

![이미지](https://github.com/kimseungwoo449/textRpg/blob/master/textRpg/textRpg_Image/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EB%93%B1.gif)

------------------------
