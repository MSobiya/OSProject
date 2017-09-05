#!/usr/bin/env

import subprocess
import MySQLdb
import time
for i in range(101,106):
	ip="192.168.0."+str(i)
	lab_no=ip[12]
	
	m=time.strftime('%Y-%m-%d %H:%M:%S')
	res=subprocess.call(['ping','-c','3',ip])
	if res==0:
		db = MySQLdb.connect(host="localhost", user="root",passwd="root",db="osproject")
		cur = db.cursor()
		cur.execute("INSERT INTO os_table (lab_no,ip,pc_no,status,last_update)values(%s,%s,%s,%s,%s)",(lab_no,ip,i,"ON",m))
		db.commit()
		db.close()
