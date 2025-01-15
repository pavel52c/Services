module.exports = {
  type: 'mysql',
  host: 'localhost',
  port: 3306,
  username: 'root',
  password: 'user',
  database: 'example',
  entities: ['src/**/**.entity.ts'],
  synchronize: true,
  autoLoadEntities: true,
};