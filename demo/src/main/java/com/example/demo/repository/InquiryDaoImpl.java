package com.example.demo.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Inquiry;

@Repository
public class InquiryDaoImpl implements InquiryDao {


	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public InquiryDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertInquiry(Inquiry inquiry) {
		jdbcTemplate.update("INSERT INTO inquiry(name, email, contents, created) VALUES(?, ?, ?, ?)",
				inquiry.getName(),inquiry.getEmail(),inquiry.getContents(),inquiry.getCreated());
	}

	@Override
	public List<Inquiry> getAll() {
		//jdbcTemplate.queryForListで使用するためのSQL文の作成
		String sql = "SELECT id, name, email, contents, created FROM inquiry";

		//上で作成したSQLを使用してSQLを発行する。戻り値はListでなかにMap<String, Object>を持っている
		//Mapは取得したレコード１レコード中の「１カラム＋値」分で、Stringには”カラム”Objectにはカラムに紐付いた値が入る
		//イメージは「１カラム＋値」分を全部取得していき、後でEntityに詰めることで１Entity ＝ １レコードにする
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);

		//最後にオブジェクトとして（Entityを詰めたList）返すために初期化しておく
		List<Inquiry> returnList = new ArrayList<>();

		//SQLの結果が入ったMapをすべてEntityに詰め直す
		//１Entity ＝ １レコードにする
		//オブジェクト指向ではオブジェクト（物）としてデータを受け渡す
		for(Map<String, Object> result:resultList) {
			////１Entity ＝ １レコードにするためにEntityをnewして初期化しておく
			Inquiry inquiry = new Inquiry();
			//Entityに値を詰め直す。DBと型が異なるためキャスト
			inquiry.setId((int)result.get("id"));
			inquiry.setName((String)result.get("name"));
			inquiry.setEmail((String)result.get("email"));
			inquiry.setContents((String)result.get("contents"));
			//SQLのDate型を受け取るには下記のようにsqlライブラリを使用してキャストする
			inquiry.setCreated(((Timestamp) result.get("created")).toLocalDateTime());
			//返却用オブジェクト（List）に格納していく
			//１Entity ＝ １レコードのイメージ
			returnList.add(inquiry);
		}
		//レコードすべてを詰めたオブジェクト（List）を返却する
		return returnList;
	}

	@Override
	public int updateInquiry(Inquiry inquiry) {
		return jdbcTemplate.update("UPDATE inquiry SET name = ?, email = ?, contents = ? WHERE id = ?",
				inquiry.getName(),inquiry.getEmail(),inquiry.getContents(),inquiry.getId());
	}

}
