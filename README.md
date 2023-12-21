## 프로젝트 구현목표
  회원가입
  로그인/로그아웃
  기초대사량 그래프 구현 음식등록한 만큼 그래프 차오르게하기
  음식 정보 검색
  음식엑셀파일 db저장
  식단공유소 게시판
  달력에 데이터저장
<br><br><br>
## 프로젝트 목적
  어플로만 나와있는 앱 웹사이트에 코딩해보기
  원본어플: YAZIO
<br><br><br>
## 프로젝트 기간
 2023.12.1 ~ 2023.12.15
  <12/16 발표>
<br><br><br>
## 이슈
### 이슈목록 
- java.lang.NullPointerException: Cannot invoke
- Caused by: java.sql.SQLSyntaxErrorException: Unknown column 'age' in 'field list
- java.lang.IllegalStateException: Cannot get a NUMERIC value from a STRING cell 엑셀 파일에서 값을 읽어오려 하는데,엑셀 파일의 셀이 숫자 형식이 아니라 문자열 형식으로 저장되어 있을때 발생
### 해결 방법
userDAO 부분에 Autowired 해주지않아서 오류발생하여 빠르게 해결
컬럼에서 AGE를 찾을수 없다고 뜬다. DB테이블에도 정확하게 만들어 놨기에 APPLICATION을 확인 username이 바뀌지 않아 발생한 오류 바꿔서 해결
DataFormatter dataFormatter = new DataFormatter 방식으로 셀값을 문자열로 읽어오도록 하는 방법을 사용하여 정상적으로 오류 해결
배포하는 과정에서도 여러가지 문제가 있었다.
bootWar 방식을 사용해보고 tomcat의 버전을 바꾸고 jdk버전도 바꾸는 등 여러가지 방법을 시도하였더니 해결되었다.
