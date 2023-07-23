# jenkins




# Ejecutcion
  docker-compose down  --volumes
  docker-compose up --build --force-recreate --no-deps -d
## TODO
- probar que un slave se conecte a jenkins master
- probar crear job con jsl
- probar JSL
- JCACS
  - https://plugins.jenkins.io/configuration-as-code/
  - en el casc.yaml el password debe leerse de una bd de parametros

- Probar configuracion de SAML / SSO
  - https://github.com/jenkinsci/matrix-auth-plugin
  - https://plugins.jenkins.io/saml/
  - https://github.com/jenkinsci/saml-plugin
  - https://plugins.jenkins.io/miniorange-saml-sp/
  - https://github.com/jenkinsci/miniorange-saml-sp-plugin/blob/master/docs/images/jenkins-sso.png


Job seed
si se desea crear un seed job con un script como se ve en https://github.com/jenkinsci/job-dsl-plugin#getting-started es necesario agregar el script a la seccion de security del casc.yaml
