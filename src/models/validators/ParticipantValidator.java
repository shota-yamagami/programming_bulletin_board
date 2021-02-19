package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Participant;
import utils.DBUtil;

public class ParticipantValidator {
	public static List<String> validate(Participant p, Boolean nameDuplicateCheckFlag, Boolean passwordCheckFlag) {
		List<String> errors = new ArrayList<String>();

		String name_error = validateName(p.getName(), nameDuplicateCheckFlag);
		if(!name_error.equals("")) {
			errors.add(name_error);
		}

		String mail_error = validateMail(p.getMail());
		if(!mail_error.equals("")) {
			errors.add(mail_error);
		}

		String password_error = validatePassword(p.getPassword(), passwordCheckFlag);
		if(!password_error.equals("")) {
			errors.add(password_error);
		}

		return errors;
	}

	// ユーザーネーム
	private static String validateName(String name, Boolean nameDuplicateCheckFlag) {
		if(name == null || name.equals("")) {
			return "ニックネームを入力してください。";
		}

		// すでに登録されている名前との重複チェック
		if(nameDuplicateCheckFlag) {
			EntityManager em = DBUtil.createEntityManager();
			long participants_count = (long)em.createNamedQuery("checkRegisteredName", Long.class).setParameter("name", name).getSingleResult();
			em.close();
			if(participants_count > 0) {
				return "入力されたお名前はすでに存在します。別のお名前でご登録ください。";
			}
		}

		return "";
	}

	// メールアドレス
	private static String validateMail(String mail) {
		if(mail == null || mail.equals("")) {
			return "メールアドレスを入力してください。";
		}

		return "";
	}

	// パスワード
	private static String validatePassword(String password, Boolean passwordCheckFlag) {
		// パスワードを変更する場合のみ実行
		if(passwordCheckFlag && (password == null || password.equals(""))) {
			return "パスワードを入力してください。";
		}

		return "";
	}
}
