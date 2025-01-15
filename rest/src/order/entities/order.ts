import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class OrderEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  number: number;

  @Column()
  dateReceive?: string;

  @Column({ type: 'text' })
  products: string;
}
