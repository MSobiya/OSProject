import subprocess
import MySQLdb
import time
for i in range(101,106):
	ip="192.168.0."+str(i)
	m=time.strftime('%Y-%m-%d %I:%M:%S')
	#print m
	res=subprocess.call(['ping','-c','3',ip])
	if res==0:
		db = MySQLdb.connect(host="localhost", user="root",passwd="root",db="osproject")
		cur = db.cursor()
		cur.execute("INSERT INTO os_table (ip,pc_no,status,last_update)values(%s,%s,%s,%s)",(ip,i,res,m))
		db.commit()
		db.close()
	#else:
		#print "OK"
