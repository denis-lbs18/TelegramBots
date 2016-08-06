package br.com.denisluna.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class ImportaUsuarios {
	private Scanner leitor;

	public Collection<Usuario> importaUsuarios(InputStream entrada) throws IOException {
		Collection<Usuario> usuarios = new LinkedHashSet<Usuario>();
		leitor = new Scanner(entrada);

		while (leitor.hasNextLine()) {
			String linha = leitor.nextLine();
			String dados[] = linha.split(";");
			
			int usuario_id = Integer.parseInt(dados[0]);
			String usuario_nome = dados[1];
			String usuario_sobrenome = dados[2];
			String usuario_username = dados[3];

			Usuario usuario = new Usuario(usuario_id, usuario_nome);

			if (!usuario_sobrenome.isEmpty())
				usuario.setSobrenome(usuario_sobrenome);

			if (!usuario_username.isEmpty())
				usuario.setUsername(usuario_username);

			usuarios.add(usuario);
		}

		return usuarios;
	}
}
