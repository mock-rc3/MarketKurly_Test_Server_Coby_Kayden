# ERD 설계
![erd](./img/ERD.png)
# [API template](https://docs.google.com/spreadsheets/d/17R5ClwLk3Qgk6IODBkNcfMbF5XQvLwX4B7tGz9-qE2A/edit?usp=sharing)
# 2022-01-08
* EC2,RDS 서버 구축 100%
* dev/prod 폴더 나누어서 서브 도메인 적용 100%
* 서브 도메인별 SSL 적용(cerbort) 100%
* git에 스프링 부트 템플릿 적용 100%
* ERD 설계 85%
* API 명세서 템플릿 작성 100%

# 2022-01-09
* ERD 리팩토링 90%
  * CheckPoint테이블을 삭제하고 Product테이블에 checkPointImageUrl컬럼 추가
  * Brand -> Product 구조를 Product -> Brand 구조로 변경하면서 중복데이터 방지
* Spring Boot Security Setting (OAuth 사용을 위한) 80%
  * SecurityConfig 작성
    * CORS 지원 활성화
    * 권한 부여
  * CustomOauth2UserService 작성 
    * SNS 로그인시 데이터 베이스 저장 구현
  * OAuthAttribute 작성
    * 무슨 SNS로 로그인하였는지 구별
  * application-oauth.yml 추가
    * Naver, Google 설정 추가
    * application.yml에 profile - oauth 추가
* 회원가입 API validator 적용으로 validation 구현 60%
  * validator 에러 메세지 출력을 위해 BaseResponse 수정
    * validation에러의 경우 메세지에 Array를 적용해서 틀린부분 모두를 알려줌(기존 1개)
* SNS Login 이슈
  * SNS로 로그인된 user의 경우 User 테이블의 컬럼과 일치하지 않는 문제(약관 동의, pwd)
  * SnsUser 테이블을 만들려고 하였으나, 기존에 User테이블과 관련된 많은 테이블들을 다시 설계해야하기 때문에 실패
  * (2022-01-11) 1차 피드백을 통해, 약관동의 또한 SNS Login에서 구현가능하다는 것을 알게되었고, pwd에 대체해서 들어갈 데이터에대해 알게됨
    * SNS Login에 경우 중요한 기능이 아니므로, 후순위로 미루게됨
* .gitignore 추가
  
# 2022-01-10
* ERD 리팩토링 92%
  * User테이블에 이용약관 동의 관련 컬럼 추가
  * 회의 결과, Coupon테이블을 product, user 용 두가지 테이블 구현
* 회원가입 API validator 적용으로 validation 구현 100%
  * Validation error 메세지 처리를 위해 refineErrors라는 메세지 정제 함수 생성(message에 Array가 가능하게 변경)
  * Validation 정규식 구현 100%
  * Validation Test 100%
  * JWT 토큰 생성
* 로그인API 구현 100%
  * Validation 구현
  * 로그인 성공시 토큰 생성
* API 명세서
  * 로그인, 회원가입 api 추가 
* UserController 리팩토링
  * 권장하지 않는 의존성 주입인 @Autowired 대신 생성자 @RequiredArgsConstructor로 의존성 주입 변경
  * @RestController에 이미 @ResponseBody 기능이 들어가 있으므로 불필요한 @ResponseBody 제거
* 휴대폰 인증 API 추가 20%

# 2022-01-11
* 휴대폰 인증 API 100% -> 카톡API로 변경 예정
* 아이디 중복확인 API 100%
* 회원정보 수정 API 100%
* 아이디 중복환인 API 100%
* 회원조회API(이메일 조회), 특정 회원조회API 추가 100%
* API 명세서
  * 휴대폰 인증 API,아이디 중복확인API, 회원정보 수정API, 회원조회API, 특정회원조회API 추가 
* 1차 피드백
  * 네이밍 직관적으로 변경
  * 테이블 더 추가하기
  * api 리스트업 진행하기
  * API template 부분에서 path variable 관련 테이블 추가
  * 오타 수정하기
  * API 역할 재분배 
  * SNS로그인의 경우 후순위로
  * 카톡 메세지API도 후순위로
  
# 2022-01-12
* 기획서 수정
  * 1차 피드백이후 API 역할 재분배와 추가 API을위해 수정
* ERD 재설계
  * naming 직관적으로 변경 (ex. PIFlag -> isPersonalInfoCollectAgree)
  * 테이블 개수 추가 (9개 -> 27개)
  * 오타 수정
  * url 혹은 많은 글이 들어가는 컬럼의 경우 TEXT로 변경
* 서버 설정 이슈
  * EC2에서 스프링 실행시 도메인을 통한 API 호출이 불가능하고 HTTPS 적용도 안되는 문제
    * 리버스 프록시 설정으로 해결
* ERD 설계 변경으로 인한 API 수정
  * User API 수정
  * 아이디 중복 확인 API URI 변경 /auth/id -> /users/check/id
* API template 수정

# 2022-01-13(몸상태가 안좋아서 작업을 많이 못했습니다.)
* User API 수정
  * GET을 사용하면 생기는 이슈
    * 프론트쪽에서 GET의 경우 리퀘스트 바디에 안들어가는 문제가 있어서 쿼리스트링으로 변경
* EC2 스프링 실행 이슈
  * java -jar ... 으로 실행할 경우 일정 시간이 지나면 꺼지는 문제가 발생하여 구글검색을 통해 nohup을 이용하여 백그라운드 실행으로 변경
  * ./gradlew 권한 문제
    * chmod +x ./gradlew로 해결

# 2022-01-14
* ERD 수정
  * Order API를 만들기 위해 필요한 컬럼들을 Order 테이블에 추가
    * deliveryInfoIdx(fk), productPrice, amountOfPayment, discountPrice, deliveryPrice, deliveryStatus, couponDiscount, rewardDiscount
  * 기본 배송지 구현을 위한 테이블 수정
    * DeliveryInfo 테이블에 isDefaultAdress 추가 
* User API 수정
  * 프론트 요청으로 GET을 사용하는 API 전부 RequestBody -> QueryString 으로 교체
  * 프론트 요청으로 User (추천인) validaion 삭제
* Order API 구현 15%
  * 주문하기 구현

# 2022-01-15
* ERD 수정
  * DeliveryInfo테이블과 Address테이블의 기능과 컬럼이 비슷하다고 판단하여 DeliveryInfo로 통합
    * DeliveryInfo 테이블에 address 컬럼 추가
    * User 테이블에 있던 address 컬럼 또한 DeliveryInfo 테이블에 통합하여 모든 주소들을 통합
* 백엔드 회의
  * Cart 테이블을 만들고 있는 코비에게 Order테이블을 만들기 위해 필요한 count컬럼 추가 요청
  * deliveryInfoIdx 컬럼이 Order테이블 보다는 Cart테이블에 있는것이 맞다고 판단하여 코비에게 요청
    * deliveryInfoIdx 컬럼을 Order테이블에서 Cart테이블로 이동
* Order API 구현 90%
  * 주문내역 조회, 주문내역 상세 조회 구현
* DeliveryInfo API 구현 80%
  * 배송지 추가하기 구현
  * 배송지 조회하기 구현
  * 배송지 수정하기 구현
* API template 작성
  * Order API 작성

# 2022-01-16
* DeliveryInfo API 구현 100%
  * 기본 배송지 설정하기 구현
    * 배송지 추가하기에서 쿼리스트링을 통해 기본 배송지 설정여부 추가
    * 배송지 수정하기에서 쿼리스트링을 통해 기본 배송지 설정여부 추가
    * 기본 배송지를 설정하기 위해서는 기존에 설정된 기본배송지를 T->F로 변환후 변경된 기본 배송지에 T값을 부여해야됨(두번의 DB 쓰기 접근)
      * 위 과정은 한번에 수행해야 하기때문에, @Transactional(rollbackOn = BaseException.class)처리
  * 특정 배송지 삭제하기 구현
  * 특정유저 배송지 조회하기 수정
    * deliveryInfoIdx, isDefaultAddress 추가
  * 특정 배송지 조회하기 구현
  * Validation 적용
* API template 작성
  * DeliveryInfo API 작성
* RDS 무료기간이 만료되는 이슈
  * 해결방법 새로운 RDS로 이사
    * 테이블, 데이터 이동 필요

# 2022-01-17
* RDS 무료기간이 만료되는 이슈
  * DB 마이그레이션 진행
    * 데이터그립을 사용해서 데이터 추출, 입력
* 쿼리에서 count를 쓸 경우 데이터가 없을 떄 null 반환
  * group by 를 같이 써서 해결 
* Order API 100%
  * 주문 취소 API 구현
  * 자주 사는 상품 구현
  * Validation 적용
* 상품 더미데이터 작성 (36개)
  * s3에 이미지 업로드
  * 과일 18개
  * 수산 18개
  
# 2022-01-18
* Like API 
  * 찜하기 구현
    * 중복 찜 방지 (트랜잭션 처리)
  * 특정 유저 찜목록 조회 구현
  * 특정 유저 찜 개수 조회
  * 특정 유저 특정 상품 찜 되어있는지 조회
  * 찜 취소하기 구현
* 마이그레이션 이후 시간이 맞지 않는 문제 발생
  * RDS 파라미터 그룹 수정으로 해결
* Coupon API
  * 쿠폰 발급받기 구현
    * 중복발급 막기
      * 사용된 쿠폰을 삭제하지 않고 status만 변경하여 중복여부 확인
  * 특정 유저 쿠폰 조회 구현
  * 장바구니에서 사용가능 쿠폰 조회 구현
    * 선택된 상품에 적용할 수 있는 쿠폰을 보여줌
  * 쿠폰 사용하기 구현 
* User API 수정
  * address, extraAddress 제거
  * 회원가입시 deliveryInfo 테이블에 주소추가(트랜잭션)
* Order API 수정
  * couponUserIdx 추가
    * 쿠폰 사용하여 주문 완료시 CouponUser테이블에서 사용된 쿠폰 제거(트랜잭션)
* 2차 피드백
  * DELETE 지양
  * ERD 기본값 변경
  * 외부 API 연동

# 2022-01-19
* 2차 피드백을 바탕으로 수정
  * DELETE문 PATCH 문으로 변경
  * patch /api/deletion -> patch /api/status 
  * ERD 기본값들 값에 맞게 수정
* 프론트 요청사항 처리
  * 자주사는 상품 이미지 추가
  * 오류 해결
* Cart API 수정
  * 생성하기 구현
  * 주문
* Order API 수정
  * 주문하기에서 기존 카트 삭제와 재생성 구현
* cartIdx
  * 로그인과 주문하기 리스폰스에 cartIdx추가

# 2022-01-20
* 토스페이먼트 연동 
  * 결제하기 구현
  * 결제취소 구현
* SNS 로그인 구현
  * 네이버
  * 구글
  * 카카오
* nginx 리버스 프록싱으로 인해 Oauth2 redirect_uri 불일치 문제
  * redirect_uri 재정의 하여서 문제 해결
* Coupon API 수정
  * 스케쥴러를 이용하여 매일 자정에 만료일 체크후 만료된 쿠폰 상태 변경
* API template 작성
