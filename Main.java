import java.util.Scanner;
import java.io.*;
import java.time.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static LocalDate date = LocalDate.now();
    static SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    static String dateFormated = sdf.format(new Date());
    static DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    static DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

    public static void main(String[] args) throws IOException {
        formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setGroupingSeparator(',');
        formatRp.setMonetaryDecimalSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);

        System.out.println("\n=========================================================================================================================================================\n");
        System.out.println("\t\t\t\t\t\t\tDATA GURU MADRASAH TSANAWIYAH NEGERI MALANG");
        System.out.println("\n=========================================================================================================================================================\n");
        System.out.print("Masukkan Jumlah Guru yang Akan Diproses : ");
        int jumlah = scanner.nextInt();
        String[][] dataGuru = new String[jumlah][9];
        double[][] dataGaji = new double[dataGuru.length][5];
        double[][] dataPotongan = new double[dataGuru.length][3];
        double[] dataTotalGaji = new double[dataGuru.length];
        double[] dataTotalPotongan = new double[dataGuru.length];
        double[][][] gajiPokok = {
            //Gol. 3A  Gol. 3B  Gol. 3C  Gol. 3D
            {{2500000, 2600000, 2700000, 2800000},// Tahun 0
            {2500000, 2600000, 2700000, 2800000},
            {2600000, 2700000, 2800000, 2900000}, // Tahun 2
            {2600000, 2700000, 2800000, 2900000},
            {2700000, 2800000, 2900000, 3000000}, // Tahun 4
            {2700000, 2800000, 2900000, 3000000},
            {2800000, 2900000, 3000000, 3100000}, // Tahun 6
            {2800000, 2900000, 3000000, 3100000},
            {2900000, 3000000, 3100000, 3200000}, // Tahun 8
            {2900000, 3000000, 3100000, 3200000},
            {3000000, 3100000, 3200000, 3300000}},// Tahun 10
            //Gol. 4A  Gol. 4B  Gol. 4C  Gol. 4D  Gol. 4E
            {{3000000, 3100000, 3200000, 3300000, 3400000},// Tahun 0
            {3000000, 3100000, 3200000, 3300000, 3400000},
            {3100000, 3200000, 3300000, 3400000, 3500000}, // Tahun 2
            {3100000, 3200000, 3300000, 3400000, 3500000},
            {3200000, 3300000, 3400000, 3500000, 3600000}, // Tahun 4
            {3200000, 3300000, 3400000, 3500000, 3600000},
            {3300000, 3400000, 3500000, 3600000, 3700000}, // Tahun 6
            {3300000, 3400000, 3500000, 3600000, 3700000},
            {3400000, 3500000, 3600000, 3700000, 3800000}, // Tahun 8
            {3400000, 3500000, 3600000, 3700000, 3800000},
            {3500000, 3600000, 3700000, 3800000, 3900000}} // Tahun 10
        };
        int kepsek = 0;
        int bendahara = 0;

        System.out.println("\n=========================================================================================================================================================\n");
        String pilihan;
        do {
            System.out.println("1. Menu Isi Data Guru");
            System.out.println("2. Menu Data Guru");
            System.out.println("3. Menu Perhitungan Gaji");
            System.out.println("4. Menu Cetak Slip Gaji");
            System.out.println("5. Menu Pencarian Guru");
            System.out.println("6. Menu Pelaporan");
            System.out.print("\nMasukkan Menu : ");
            int menu = scanner.nextInt();
            System.out.println();
            if(menu == 1) {
                System.out.println("=========================================================================================================================================================\n");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
                for(int i = 0; i < dataGuru.length; i++) {
                    System.out.println("\nData Guru ke-" + (i+1));
                    for(int j = 0; j < dataGuru[0].length; j++) {
                        if(j == 0) {
                            System.out.print("\nMasukkan Nama Guru           : ");    
                        } else if(j == 1) {
                            // Format NIP: 10 digit angka
                            System.out.print("Masukkan NIP                 : ");
                        } else if(j == 2) {
                            System.out.print("Masukkan Jenis Kelamin (L/P) : ");
                        } else if(j == 3) {
                            System.out.print("Masukkan Tempat Lahir        : ");
                        } else if(j == 4) {
                            System.out.print("Masukkan Tanggal Lahir       : ");
                        } else if(j == 5) {
                            System.out.print("Masukkan Alamat              : ");
                        } else if(j == 6) {
                            System.out.print("Masukkan Pendidikan Terakhir : ");
                        } else if(j == 7) {
                            /* Format Jabatan:
                            "Kep. Madrasah" (Wajib)
                            "Guru Umum"
                            "Guru & BK"
                            "Guru & Bend." (Wajib)
                            "Guru & Labor."
                            "Guru & Perpus" */
                            System.out.print("Masukkan Jabatan             : ");
                        } else if(j == 8) {
                            System.out.print("Masukkan Nomor Telepon       : ");
                        }
                        dataGuru[i][j] = reader.readLine();
                    }
                    System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
                System.out.println("\n=========================================================================================================================================================\n");
                // Untuk mengetahui siapa Kepala Madrasah dan Bendahara
                for(int i = 0; i < dataGuru.length; i++) {
                    if(dataGuru[i][7].equalsIgnoreCase("Kep. Madrasah")) {
                        kepsek = i;
                    } else if(dataGuru[i][7].equalsIgnoreCase("Guru & Bend.")) {
                        bendahara = i;
                    }
                }
            } else if(menu == 2) {
                MenuData(dataGuru, kepsek);
            } else if(menu == 3) {
                System.out.println("=========================================================================================================================================================\n");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                for(int i = 0; i < dataGaji.length; i++) {
                    String nikah = "";
                    int anak = 0;
                    System.out.println("Data Gaji A.N " + dataGuru[i][0] + ", S.Pd");
                    System.out.println("Nip. " + dataGuru[i][1]);
                    for(int j = 0; j < dataGaji[0].length; j++) {
                        if(j == 0) {
                            /* Golongan:
                               3a - 3d
                               4a - 4e */
                            System.out.print("\nMasukkan Golongan        : ");
                            String gol = scanner.next();
                            // Masa kerja dalam tahun
                            System.out.print("Masukkan Masa Kerja      : ");
                            int thn = scanner.nextInt();
                            Golongan(i, gol, thn, dataGaji, gajiPokok);
                        } else if(j == 1) {
                            System.out.print("Sudah Menikah (Y/T)      : ");
                            nikah = scanner.next();
                            if(nikah.equalsIgnoreCase("y")) {
                                // Tunjangan istri = gaji pokok * 10%
                                dataGaji[i][j] = dataGaji[i][0] * 0.1;
                            }
                        } else if(j == 2) {
                            if(nikah.equalsIgnoreCase("y")) {
                                System.out.print("Masukkan Jumlah Anak     : ");
                                anak = scanner.nextInt();
                                // Tunjangan anak = gaji pokok * 2.5% * jumlah anak (Maks. 2 anak)
                                if(anak >= 0 && anak <= 2) {
                                    dataGaji[i][j] = dataGaji[i][0] * 0.025 * anak;
                                } else if(anak > 2) {
                                    anak = 2;
                                    dataGaji[i][j] = dataGaji[i][0] * 0.025 * anak;
                                }
                            } else {
                                System.out.println("Masukkan Jumlah Anak     : 0");
                            }
                        } else if(j == 3) {
                            if(dataGuru[i][7].equalsIgnoreCase("Kep. Madrasah")) {
                                dataGaji[i][j] = 1260000;
                                System.out.println("Tunjangan Struktural     : " + kursIndonesia.format(dataGaji[i][j]));
                            } else {
                                dataGaji[i][j] = 360000;
                                System.out.println("Tunjangan Fungsional     : " + kursIndonesia.format(dataGaji[i][j]));
                            }
                        } else if(j == 4) {
                            // Tunjangan beras = nominal tunjangan beras * jumlah anggota keluarga
                            if(nikah.equalsIgnoreCase("y")) {
                                dataGaji[i][j] = 70000 * (2 + anak);
                                System.out.println("Tunjangan Beras          : " + kursIndonesia.format(dataGaji[i][j]));
                            } else {
                                dataGaji[i][j] = 70000;
                                System.out.println("Tunjangan Beras          : " + kursIndonesia.format(dataGaji[i][j]));
                            }
                        }
                        dataTotalGaji[i] += dataGaji[i][j];
                    }
                    System.out.println("Jumlah Penghasilan kotor : " + kursIndonesia.format(dataTotalGaji[i]));
                    System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                }
                for(int i = 0; i < dataPotongan.length; i++) {
                    for(int j = 0; j < dataPotongan[0].length; j++) {
                        if(j == 0) {
                            // Iuran Wajib Pegawai = gaji pokok * 8%
                            dataPotongan[i][j] = dataGaji[i][0] * 0.08;
                        } else if(j == 1) {
                            // BPJS = penghasilan kotor * 1%
                            dataPotongan[i][j] = dataTotalGaji[i] * 0.01;
                        } else if(j == 2) {
                            // Potongan Taperum = penghasilan kotor * 3%
                            dataPotongan[i][j] = dataTotalGaji[i] * 0.03;
                        }
                        dataTotalPotongan[i] += dataPotongan[i][j];
                    }
                }
                System.out.println("=========================================================================================================================================================\n");
            } else if(menu == 4) {
                MenuGaji(dataGuru, dataGaji, dataPotongan, dataTotalGaji, dataTotalPotongan, kepsek, bendahara);
            } else if(menu == 5) {
                MenuPencarian(dataGuru, dataGaji, dataPotongan, dataTotalGaji, dataTotalPotongan, kepsek, bendahara);
            } else if(menu == 6) {
                MenuPelaporan(dataGuru, dataGaji, dataPotongan, dataTotalGaji, dataTotalPotongan, kepsek, bendahara);
            } else {
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                System.out.println("Menu Tidak Ditemukan...");
                System.out.println("\n=========================================================================================================================================================\n");
            }
            System.out.print("Kembali ke Menu Utama (Y/T) ");
            pilihan = scanner.next();
            System.out.println("\n=========================================================================================================================================================\n");
        } while(pilihan.equalsIgnoreCase("y"));
    }

    static double Golongan(int x, String golongan, int tahun, double[][] data, double[][][] gaji) {
        switch(golongan) {
            case "3a":
                data[x][0] = gaji[0][tahun][0]; break;
            case "3b":
                data[x][0] = gaji[0][tahun][1]; break;
            case "3c":
                data[x][0] = gaji[0][tahun][2]; break;
            case "3d":
                data[x][0] = gaji[0][tahun][3]; break;
            case "4a":
                data[x][0] = gaji[1][tahun][0]; break;
            case "4b":
                data[x][0] = gaji[1][tahun][1]; break;
            case "4c":
                data[x][0] = gaji[1][tahun][2]; break;
            case "4d":
                data[x][0] = gaji[1][tahun][3]; break;
            case "4e":
                data[x][0] = gaji[1][tahun][4]; break;
            default:
                data[x][0] = 0;
        }
        return data[x][0];
    }

    static void MenuData(String[][] guru, int kepsek) {
        System.out.println("=========================================================================================================================================================\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("\t\t\t\t\t\t\tDATA PENDIDIK DAN TENAGA KEPENDIDIKAN");
        System.out.println("\t\t\t\t\t\t\t  MADRASAH TSANAWIYAH NEGERI MALANG");
        System.out.println("\t\t\t\t\t\t\t      TAHUN PELAJARAN " + date.getYear() + "/" + (date.getYear() + 1));
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| NO |    NIP     |        NAMA\t\t| L/P |  TEMPAT LAHIR\t|     TANGGAL LAHIR\t|\t  ALAMAT\t| PEND |    JABATAN\t|      TLP\t|");
        System.out.println("|----|------------|---------------------|-----|-----------------|-----------------------|-----------------------|------|----------------|---------------|");
        for(int i = 0; i < guru.length; i++) {
            System.out.print("| " + (i+1) + ". | ");
            for(int j = 0; j < guru[0].length; j++) {
                if(j == 0) {
                    System.out.print(guru[i][1] + " | ");
                } else if(j == 1) {
                    System.out.print(guru[i][0] + ", S.Pd\t|  ");
                } else if(j == 2) {
                    System.out.print(guru[i][j] + "  |    ");
                } else if(j == 3) {
                    System.out.print(guru[i][j] + "\t|   ");
                } else if(j == 4) {
                    System.out.print(guru[i][j] + "\t|    ");
                } else if(j == 5) {
                    System.out.print(guru[i][j] + "\t|  ");
                } else if(j == 6) {
                    System.out.print(guru[i][j] + "  | ");
                } else if(j == 7) {
                    System.out.print(guru[i][j] + "\t| ");
                } else if(j == 8) {
                    System.out.print(guru[i][j] + "\t|");
                }
            }
            System.out.println();
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMalang, " + dateFormated);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tKepala Madrasah\n\n");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + guru[kepsek][0] + ", S.Pd");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tNip. " + guru[kepsek][1]);
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\n=========================================================================================================================================================\n");
    }

    static void MenuGaji(String[][] guru, double[][] gaji, double[][] potongan, double[] totalKotor, double[] totalPotongan, int kepsek, int bendahara) {
        System.out.println("=========================================================================================================================================================\n");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        for(int i = 0; i < guru.length; i++) {
            System.out.println("\tPERINCIAN GAJI BULAN " + date.getMonth() + " " + date.getYear());
            System.out.println("\t      A.N " + guru[i][0] + ", S.Pd");
            System.out.println("\t\t  NIP " + guru[i][1]);
            System.out.println();
            for(int j = 0; j < 11; j++) {
                if(j == 0) {
                    System.out.println("I.     Penghasilan :");
                    System.out.println("1.  Gaji Pokok\t\t\t" + kursIndonesia.format(gaji[i][j]));
                } else if(j == 1) {
                    System.out.println("2.  Tunjangan Istri/Suami\t" + kursIndonesia.format(gaji[i][j]));
                } else if(j == 2) {
                    System.out.println("3.  Tunjangan Anak\t\t" + kursIndonesia.format(gaji[i][j]));
                } else if(j == 3) {
                    if(guru[i][7].equalsIgnoreCase("Kep. Madrasah")) {
                        System.out.println("4.  Tunjangan Struktural\t" + kursIndonesia.format(gaji[i][j]));
                    } else {
                        System.out.println("4.  Tunjangan Fungsional\t" + kursIndonesia.format(gaji[i][j]));
                    }
                } else if(j == 4) {
                    System.out.println("5.  Tunjangan Beras\t\t" + kursIndonesia.format(gaji[i][j]));
                } else if(j == 5) {
                    System.out.println("6.  Jumlah Penghasilan kotor......................" + kursIndonesia.format(totalKotor[i]));
                } else if(j == 6) {
                    System.out.println("II.    Potongan :");
                    System.out.println("01. Iuran Wajib Pegawai\t\t" + kursIndonesia.format(potongan[i][0]));
                } else if(j == 7) {
                    System.out.println("02. BPJS\t\t\t" + kursIndonesia.format(potongan[i][1]));
                } else if(j == 8) {
                    System.out.println("03. Potongan Taperum\t\t" + kursIndonesia.format(potongan[i][2]));
                } else if(j == 9) {
                    System.out.println("04. Jumlah Potongan..............................." + kursIndonesia.format(totalPotongan[i]));
                } else if(j == 10) {
                    System.out.println("05. Jumlah Penghasilan bersih yang dibayarkan     " + kursIndonesia.format(totalKotor[i] - totalPotongan[i]));
                }
            }
            System.out.println("\nMengetahui\t\t\tMalang, " + dateFormated);
            System.out.println("Kepala Madrasah\t\t\tBendahara Gaji\n\n");
            System.out.println(guru[kepsek][0] + ", S.Pd\t\t" + guru[bendahara][0] + ", S.Pd");
            System.out.println("Nip. " + guru[kepsek][1] + "\t\t\tNip. " + guru[bendahara][1]);
            System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        }
        System.out.println("=========================================================================================================================================================\n");
    }

    static void MenuPencarian(String[][] guru, double[][] gaji, double[][] potongan, double[] totalGaji, double[] totalPotongan, int kepsek, int bendahara) throws IOException {
        System.out.println("=========================================================================================================================================================\n");
        String lanjutkan;
        do {
            System.out.print("Masukkan Nama Guru : ");
            String nama = reader.readLine();
            System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            int index = 0;
            for(int i = 0; i < guru.length; i++) {
                if(nama.equalsIgnoreCase(guru[i][0])) {
                    index = i;
                }
            }
			String lainnya;
            do {
                System.out.println("1. Menu Data Guru");
                System.out.println("2. Menu Slip Gaji");
                System.out.print("\nMasukkan Menu : ");
                int menu = scanner.nextInt();
                System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                if(index <= guru.length) {
                    if(menu == 1) {
                        for(int i = 0; i < guru[0].length; i++) {
                            if(i == 0) {
                                System.out.println("Nama                : " + guru[index][i] + ", S.Pd");
                            } else if(i == 1) {
                                System.out.println("NIP                 : " + guru[index][i]);
                            } else if(i == 2) {
                                if(guru[index][i].equalsIgnoreCase("L")) {
                                    System.out.println("Jenis Kelamin       : Laki-laki");
                                } else {
                                    System.out.println("Jenis Kelamin       : Perempuan");
                                }
                            } else if(i == 3) {
                                System.out.println("Tempat Lahir        : " + guru[index][i]);
                            } else if(i == 4) {
                                System.out.println("Tanggal Lahir       : " + guru[index][i]);
                            } else if(i == 5) {
                                System.out.println("Alamat              : " + guru[index][i]);
                            } else if(i == 6) {
                                System.out.println("Pendidikan Terakhir : " + guru[index][i]);
                            } else if(i == 7) {
                                System.out.println("Jabatan             : " + guru[index][i]);
                            } else if(i == 8) {
                                System.out.println("Nomor Telepon       : " + guru[index][i]);
                            }
                        }
                    } else if(menu == 2) {
						System.out.println("\tPERINCIAN GAJI BULAN " + date.getMonth() + " " + date.getYear());
						System.out.println("\t      A.N " + guru[index][0] + ", S.Pd");
						System.out.println("\t\t  NIP " + guru[index][1]);
						System.out.println();
						for(int i = 0; i < 11; i++) {
							if(i == 0) {
								System.out.println("I.     Penghasilan :");
								System.out.println("1.  Gaji Pokok\t\t\t" + kursIndonesia.format(gaji[index][i]));
							} else if(i == 1) {
								System.out.println("2.  Tunjangan Istri/Suami\t" + kursIndonesia.format(gaji[index][i]));
							} else if(i == 2) {
								System.out.println("3.  Tunjangan Anak\t\t" + kursIndonesia.format(gaji[index][i]));
							} else if(i == 3) {
								if(guru[index][7].equalsIgnoreCase("Kep. Madrasah")) {
									System.out.println("4.  Tunjangan Struktural\t" + kursIndonesia.format(gaji[index][i]));
								} else {
									System.out.println("4.  Tunjangan Fungsional\t" + kursIndonesia.format(gaji[index][i]));
								}
							} else if(i == 4) {
								System.out.println("5.  Tunjangan Beras\t\t" + kursIndonesia.format(gaji[index][i]));
							} else if(i == 5) {
								System.out.println("6.  Jumlah Penghasilan kotor......................" + kursIndonesia.format(totalGaji[index]));
							} else if(i == 6) {
								System.out.println("II.    Potongan :");
								System.out.println("01. Iuran Wajib Pegawai\t\t" + kursIndonesia.format(potongan[index][0]));
							} else if(i == 7) {
								System.out.println("02. BPJS\t\t\t" + kursIndonesia.format(potongan[index][1]));
							} else if(i == 8) {
								System.out.println("03. Potongan Taperum\t\t" + kursIndonesia.format(potongan[index][2]));
							} else if(i == 9) {
								System.out.println("04. Jumlah Potongan..............................." + kursIndonesia.format(totalPotongan[index]));
							} else if(i == 10) {
								System.out.println("05. Jumlah Penghasilan bersih yang dibayarkan     " + kursIndonesia.format(totalGaji[index] - totalPotongan[index]));
							}
						}
						System.out.println("\nMengetahui\t\t\tMalang, " + dateFormated);
						System.out.println("Kepala Madrasah\t\t\tBendahara Gaji\n\n");
						System.out.println(guru[kepsek][0] + ", S.Pd\t\t" + guru[bendahara][0] + ", S.Pd");
						System.out.println("Nip. " + guru[kepsek][1] + "\t\t\tNip. " + guru[bendahara][1]);
					} else {
						System.out.println("Menu Tidak Ditemukan...");
					}
				}
                System.out.println("\n=========================================================================================================================================================\n");
                System.out.print("Pilih Menu yang Lain (Y/T) ");
                lainnya = scanner.next();
                System.out.println("\n=========================================================================================================================================================\n");
            } while(lainnya.equalsIgnoreCase("y"));
            System.out.print("Lanjutkan Pencarian (Y/T) ");
            lanjutkan = scanner.next();
            if(lanjutkan.equalsIgnoreCase("y")) {
                System.out.println("\n=========================================================================================================================================================\n");
            }
        } while(lanjutkan.equalsIgnoreCase("y"));
        System.out.println("\n=========================================================================================================================================================\n");
    }

    static void MenuPelaporan(String[][] guru, double[][] gaji, double[][] potongan, double[] totalGaji, double[] totalPotongan, int kepsek, int bendahara) {
        double[] hitungGaji = new double[gaji.length];
        double[] hitungTotalGaji = new double[gaji[0].length];
        double[] hitungTotalPotongan = new double[potongan[0].length];
        double total = 0;
        for(int i = 0; i < gaji.length; i++) {
            hitungGaji[i] = totalGaji[i] - totalPotongan[i];
            total += hitungGaji[i];
            for(int j = 0; j < gaji[0].length; j++) {
                hitungTotalGaji[j] += gaji[i][j];
            } for(int k = 0; k < potongan[0].length; k++) {
                hitungTotalPotongan[k] += potongan[i][k];
            }
        }
        System.out.println("=========================================================================================================================================================================================\n");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("\t\t\t\t\t\tDAFTAR PENERIMAAN TRANSPORT GURU DAN KARYAWAN MADRASAH TSANAWIYAH NEGERI MALANG");
        System.out.println("\t\t\t\t\t\t\t\tJL. SOEKARNO HATTA 9 MALANG TELP. 0341 - 404424\n");
        System.out.println("      BULAN :\t" + date.getMonth() + "-" + date.getDayOfMonth());
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|    |\t\t\t|\t\t|\t\t\t\t    PENERIMAAN\t\t\t\t\t|\t\t     POTONGAN\t\t\t|\t\t|");
        System.out.println("| NO |       NAMA\t|    JABATAN\t|-------------------------------------------------------------------------------|-----------------------------------------------|    DITERIMA\t|");
        System.out.println("|    |\t\t\t|\t\t|     Pokok\t|    T. Istri\t|    T. Anak\t| T. Fungsional\t|    T. Beras\t|  Iuran Wajib\t|      BPJS\t|    Taperum\t|\t\t|");
        System.out.println("|----|------------------|---------------|---------------|---------------|---------------|---------------|---------------|---------------|---------------|---------------|---------------|");
        for(int i = 0; i < guru.length; i++) {
            System.out.print("| " + (i+1) + ". | ");
            for(int j = 0; j < 11; j++) {
                if(j == 0) {
                    System.out.print(guru[i][0]);
                } else if(j == 1) {
                    System.out.print("\t| " + guru[i][7]);
                } else if(j == 2) {
                    System.out.print("\t| Rp. " + gaji[i][0]);
                } else if(j == 3) {
                    System.out.print("\t| Rp. " + gaji[i][1]);
                } else if(j == 4) {
                    System.out.print("\t| Rp. " + gaji[i][2]);
                } else if(j == 5) {
                    System.out.print("\t| Rp. " + gaji[i][3]);
                } else if(j == 6) {
                    System.out.print("\t| Rp. " + gaji[i][4]);
                } else if(j == 7) {
                    System.out.print("\t| Rp. " + potongan[i][0]);
                } else if(j == 8) {
                    System.out.print("\t| Rp. " + potongan[i][1]);
                } else if(j == 9) {
                    System.out.print("\t| Rp. " + potongan[i][2]);
                } else if(j == 10) {
                    System.out.print("\t| Rp. " + hitungGaji[i] + "\t|");
                }
            }
            System.out.println();
        }
        System.out.println("|---------------------------------------|---------------|---------------|---------------|---------------|---------------|---------------|---------------|---------------|---------------|");
        for(int i = 0; i < 9; i++) {
            if(i == 0) {
                System.out.print("|\t\t  JUMLAH\t\t| Rp. " + hitungTotalGaji[0]);
            } else if(i == 1) {
                System.out.print("\t| Rp. " + hitungTotalGaji[1]);
            } else if(i == 2) {
                System.out.print("\t| Rp. " + hitungTotalGaji[2]);
            } else if(i == 3) {
                System.out.print("\t| Rp. " + hitungTotalGaji[3]);
            } else if(i == 4) {
                System.out.print("\t| Rp. " + hitungTotalGaji[4]);
            } else if(i == 5) {
                System.out.print("\t| Rp. " + hitungTotalPotongan[0]);
            } else if(i == 6) {
                System.out.print("\t| Rp. " + hitungTotalPotongan[1]);
            } else if(i == 7) {
                System.out.print("\t| Rp. " + hitungTotalPotongan[2]);
            } else if(i == 8) {
                System.out.print("\t| Rp. " + total + "\t|");
            }
        }
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("      Mengetahui\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMalang, " + dateFormated);
        System.out.println("      Kepala Madrasah\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tBendhr Sie Pendidikan\n\n");
        System.out.println("      " + guru[kepsek][0] + ", S.Pd \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + guru[bendahara][0] + ", S.Pd");
        System.out.println("      Nip. " + guru[kepsek][1] + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tNip. " + guru[bendahara][1]);
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("=========================================================================================================================================================================================\n");
    }
}